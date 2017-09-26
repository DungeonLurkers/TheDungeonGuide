package tk.avabin.tdg.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
@Configuration
open class SecurityConfig(
    private @Autowired val dataSource: DataSource,
    private @Autowired val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
            .antMatchers("/admin/**")
            .access("hasRole('ROLE_ADMIN')")
            .and()
            .csrf().disable()

    }

    @Bean
    open fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}