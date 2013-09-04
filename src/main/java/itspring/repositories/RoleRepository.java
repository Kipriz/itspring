package itspring.repositories;

import itspring.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Andrey Volkov
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    public List<Role> findAll();
}
