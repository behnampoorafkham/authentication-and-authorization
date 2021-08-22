package examsecurity.demo.SecurityConfig;

import examsecurity.demo.SecurityConfig.Filter.CsrfTokenLogger;
import examsecurity.demo.SecurityConfig.Filter.RequestFilter;
import examsecurity.demo.SecurityConfig.Provider.CustomAuthenticationProvider;
import examsecurity.demo.SecurityConfig.Response.Failure;
import examsecurity.demo.SecurityConfig.Response.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;

@Configuration
@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Failure failure;

    @Autowired
    Success success;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.formLogin().successHandler(success)
//                        .failureHandler(failure);

        http.addFilterBefore(new CsrfTokenLogger(), CsrfFilter.class).authorizeRequests()
                .anyRequest().hasAuthority("User");

    }

    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}