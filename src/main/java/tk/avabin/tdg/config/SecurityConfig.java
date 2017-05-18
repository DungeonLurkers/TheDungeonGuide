package tk.avabin.tdg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import tk.avabin.tdg.beans.Controllers.RequestAwareAuthenticationSuccessHandler;
import tk.avabin.tdg.beans.RestAuthenticationEntryPoint;

/**
 * Created by Avabin on 09.04.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ClientDetailsService clientDetailsService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private final RequestAwareAuthenticationSuccessHandler requestAwareAuthenticationSuccessHandler;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Admin").password("testadmin").roles("ADMIN")
                .and()
                .withUser("User").password("testuser").roles("USER");
    }

    @Autowired
    public SecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                          RequestAwareAuthenticationSuccessHandler requestAwareAuthenticationSuccessHandler,
                          ClientDetailsService clientDetailsService) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.requestAwareAuthenticationSuccessHandler = requestAwareAuthenticationSuccessHandler;
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll();
    }

    @Bean("authenticationManagerBean")
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(name = "tokenStore")
    public TokenStore getTokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean("tokenStoreUserApprovalHandler")
    @Autowired
    public TokenStoreUserApprovalHandler getTokenStoreUserApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore getApprovalStore(TokenStore tokenStore) {
        TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
        tokenApprovalStore.setTokenStore(tokenStore);
        return tokenApprovalStore;
    }

}
