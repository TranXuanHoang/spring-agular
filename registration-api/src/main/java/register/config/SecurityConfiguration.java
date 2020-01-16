package register.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable(); //explicitly disable HTTP Basic
        http
            // Explicitly ask for a non-stateless session creation policy
            .sessionManagement()
                .maximumSessions(1)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
            // Allow CORS's pre-flight check requests to go through
            .cors()
                .and()
            // Ask for all requests have to be authenticated
            .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
            // CSRF protection
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    // @formatter:on
}