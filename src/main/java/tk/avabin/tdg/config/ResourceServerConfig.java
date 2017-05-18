package tk.avabin.tdg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Created by Avabin on 16.05.2017.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    private static final String RESOURCE_ID = "THEDUNGEONGUIDE_REST";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.anonymous().disable()
       .requestMatchers().antMatchers("/api/**")
               .and().authorizeRequests()
               .antMatchers("/api/**").access("hasRole('ADMIN')")
               .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
