package single.signon.authserver.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@RestController
public class AuthenticationController {
    @RequestMapping("auth-info")
    public AuthInfo getAuthInfo() {
        //
        UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        UserDetails admin = users
            .username("admin")
            .password("password")
            .roles("USER","ADMIN")
            .build();
        // TODO
        return new AuthInfo(user.getUsername(), user.getPassword(), "password", user, admin);
    }
}

@Data
@ToString
@AllArgsConstructor
class AuthInfo {
    private String userName;
    private String token;
    private String password;
    private UserDetails user;
    private UserDetails admin;
}