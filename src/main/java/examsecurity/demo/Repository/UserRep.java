package examsecurity.demo.Repository;

import examsecurity.demo.config.UserDetail.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRep extends CrudRepository<User, Integer> {
}
