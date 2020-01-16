package spring.angular.backend;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.security.SecurityProperties;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
// import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
// import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
// import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.ToString;

@SpringBootApplication
@RestController
public class BackEndApplication {

	@RequestMapping("resource")
	@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
	public Message home() {
		Message message = new Message("Hello World Backend");
		System.out.println(message);
		return message;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	// @formatter:off
	// @Configuration
	// @Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
	// protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	// 	@Override
	// 	protected void configure(HttpSecurity http) throws Exception {
	// 		// Allow CORS's pre-flight check requests to go through
	// 		http
	// 			.sessionManagement()
	// 				.maximumSessions(1)
	// 				.and()
	// 				.sessionCreationPolicy(SessionCreationPolicy.NEVER)
	// 				.and()
	// 			.cors()
	// 				.and()
	// 			.authorizeRequests()
	// 				.anyRequest()
	// 				.authenticated()
	// 			;
	// 	}
	// } // end class SecurityConfiguration
	// @formatter:on

	// @formatter:off
	@Configuration
	public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().disable(); //explicitly disable HTTP Basic in the resource server
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
	}
	// @formatter:on

	// START SESSION CONFIGURATION
	/**
	 * Configures Spring Session backed by Redis.
	 * 
	 * @see {@link https://docs.spring.io/spring-session/docs/2.2.0.RELEASE/reference/html5/guides/boot-redis.html}
	 *      {@link https://docs.spring.io/spring-session/docs/2.2.0.RELEASE/reference/html5/guides/java-rest.html}
	 */
	// @Configuration
	// @EnableRedisHttpSession
	// protected static class HttpSessionConfig {
	// 	@Bean
	// 	public LettuceConnectionFactory connectionFactory() {
	// 		// See
	// 		// https://docs.spring.io/spring-data/data-redis/docs/2.2.0.RELEASE/reference/html/#redis:connectors:lettuce
	// 		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
	// 	}

	// 	/**
	// 	 * Specifies Spring to look in the header ("X-Auth-Token" by default)
	// 	 * instead of the default (cookie named "JSESSIONID") to authenticate users.
	// 	 */
	// 	@Bean
	// 	public HttpSessionIdResolver httpSessionIdResolver() {
	// 		return HeaderHttpSessionIdResolver.xAuthToken();
	// 	}
	// } // end class HttpSessionConfig
	// END SESSION CONFIGURATION

}

@Data
@ToString
class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
