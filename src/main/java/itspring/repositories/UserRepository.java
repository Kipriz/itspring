package itspring.repositories;

import itspring.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andrey Volkov
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByLogin(String login);

}
