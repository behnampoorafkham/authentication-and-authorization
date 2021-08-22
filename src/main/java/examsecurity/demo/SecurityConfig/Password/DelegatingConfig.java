package examsecurity.demo.SecurityConfig.Password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class DelegatingConfig {
    // Omitted code
    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        Random random = new Random();
        int x = random.nextInt();
        if (x > 1000 && x < 1500) {
            return new DelegatingPasswordEncoder("noop", encoders);
        } else if (x > 500 && x < 1000) {
            return new DelegatingPasswordEncoder("bcrypt", encoders);
        }
        else {
            return new DelegatingPasswordEncoder("scrypt", encoders);
        }
    }
}