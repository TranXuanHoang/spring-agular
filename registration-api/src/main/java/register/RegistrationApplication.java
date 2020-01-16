package register;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@SpringBootApplication
@RestController
public class RegistrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }

    @GetMapping("/register/info/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = { "x-auth-token", "x-requested-with", "x-xsrf-token" })
    public Information getInfoById(
        @PathVariable long id, HttpServletRequest req, HttpServletResponse res
    ) {
        // Return info
        Random rand = new Random();
        String username = Math.abs(rand.nextInt())
            + "-" + Math.abs(rand.nextInt())
            + "-" + Math.abs(rand.nextInt());
        Information info = new Information(id, username);

        System.out.println(info.toString());

        return info;
    }
}

@Data
@AllArgsConstructor
@ToString
class Information {
    private Long id;
    private String username;
}