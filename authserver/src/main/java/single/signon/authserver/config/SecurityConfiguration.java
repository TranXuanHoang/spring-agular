package single.signon.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // Ask for all requests have to be authenticated
            .authorizeRequests()
                .antMatchers("/login", "/oauth/authorize").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .httpBasic()
                .and()
            // Allow CORS's pre-flight check requests to go through
            .cors()
                .and()
            // CSRF protection
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
            // Explicitly ask for a non-stateless session creation policy
            /*.sessionManagement()
                .maximumSessions(1)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)*/;
    }
    // @formatter:on

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.js").antMatchers("/**.ico");
    }
}