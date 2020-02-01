package spring.angular;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
// import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableZuulProxy
@EnableOAuth2Sso // Enable OAuth2 Single Sign On (SSO)
public class SpringAngularApplication extends WebSecurityConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(SpringAngularApplication.class, args);
  }

  // @RequestMapping("/")
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("page", "Home Page");
    return model;
  }

  // @RequestMapping("/user")
  public Principal user(Principal user) {
    // TODO
    return user;
  }

  @RequestMapping("/token")
  public Map<String, String> token(HttpSession session) {
    return Collections.singletonMap("token", session.getId());
  }

  // @formatter:off
  // tag::config[]
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .logout()
        .logoutSuccessUrl("/")
        .and()
      .authorizeRequests()
        .antMatchers("/index.html", "/app.html", "/", "/home", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
      .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }
  // end::config[]
  // @formatter:on

  @Bean
  protected OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource,
      OAuth2ClientContext context) {
    return new OAuth2RestTemplate(resource, context);
  }

  // @Configuration
  // @Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
  // protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  //   // @formatter:off
  //   // tag::config[]
  //   @Override
  //   protected void configure(HttpSecurity http) throws Exception {
  //     http
  //         .httpBasic()
  //           .and()
  //         .authorizeRequests()
  //           .antMatchers("/index.html", "/", "/home", "/login").permitAll()
  //           .anyRequest().authenticated()
  //           .and()
  //         .csrf()
  //           .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  //   }
  //   // end::config[]
  //   // @formatter:on

  //   @Override
  //   public void configure(WebSecurity web) throws Exception {
  //     web.ignoring().antMatchers("/**.js").antMatchers("/**.ico");
  //   }
  // } // end class SecurityConfiguration

  /**
   * Configures Redis manually with Spring.
   * 
   * @see {@link https://docs.spring.io/spring-session/docs/2.2.0.RELEASE/reference/html5/guides/boot-redis.html}
   */
  // @EnableRedisHttpSession
  // protected static class HttpSessionConfiguration {
  // }

}