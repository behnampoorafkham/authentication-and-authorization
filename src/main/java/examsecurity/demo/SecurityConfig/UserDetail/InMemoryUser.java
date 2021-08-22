package examsecurity.demo.SecurityConfig.UserDetail;

import examsecurity.demo.Repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class InMemoryUser implements UserDetailsService {

    private Iterable<User> users;

    @Autowired
    UserRep userRep;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        users = userRep.findAll();
        for (User item : users){
            if (item.getUsername().equals(s)){
                return new SecurityUser(new User(item.getId(),item.getUsername()
                        ,item.getPassword(),item.getAuthority()));
            }
        }
        return null;
    }
}