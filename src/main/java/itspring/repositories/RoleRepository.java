package itspring.repositories;

import itspring.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Andrey Volkov
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
