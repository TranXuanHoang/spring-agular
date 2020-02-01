package main.java.spring.angular.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
// @Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  // @formatter:off
    // tag::config[]
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .httpBasic()
            .and()
          .authorizeRequests()
            .antMatchers("/index.html", "/", "/home", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
          .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    // end::config[]
    // @formatter:on

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/**.js").antMatchers("/**.ico");
  }
} // end class SecurityConfiguration