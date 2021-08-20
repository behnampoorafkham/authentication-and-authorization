package examsecurity.demo.Repository;

import examsecurity.demo.SecurityConfig.UserDetail.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRep extends CrudRepository<User, Integer> {
}
