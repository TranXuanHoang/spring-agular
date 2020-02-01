// package spring.angular.backend.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
// import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
// import org.springframework.session.web.http.HttpSessionIdResolver;

// /**
//  * Configures Spring Session backed by Redis.
//  * 
//  * @see {@link https://docs.spring.io/spring-session/docs/2.2.0.RELEASE/reference/html5/guides/boot-redis.html}
//  *      {@link https://docs.spring.io/spring-session/docs/2.2.0.RELEASE/reference/html5/guides/java-rest.html}
//  */
// @Configuration
// @EnableRedisHttpSession
// public class HttpSessionConfiguration {
//     @Bean
//     public LettuceConnectionFactory connectionFactory() {
//         // See
//         // https://docs.spring.io/spring-data/data-redis/docs/2.2.0.RELEASE/reference/html/#redis:connectors:lettuce
//         return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
//     }

//     /**
//      * Specifies Spring to look in the header ("X-Auth-Token" by default) instead of
//      * the default (cookie named "JSESSIONID") to authenticate users.
//      */
//     @Bean
//     public HttpSessionIdResolver httpSessionIdResolver() {
//         return HeaderHttpSessionIdResolver.xAuthToken();
//     }
// } // end class HttpSessionConfig