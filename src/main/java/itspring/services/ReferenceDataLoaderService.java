package itspring.services;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrey Volkov
 */
@Service
public class ReferenceDataLoaderService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void loadData() {
        /* if there is not data, then load it */
        if (roleRepository.count() == 0) {
            Map<String, Role> roles = loadRoles();

            loadUsers(roles);
        }
    }

    private Map<String, Role> loadRoles() {
        roleRepository.save(new Role("ROLE_ADMIN", "Administrator"));
        roleRepository.save(new Role("ROLE_USER", "User"));

        Map<String, Role> codeToRole = new HashMap<>();
        Iterable<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            codeToRole.put(role.getCode(), role);
        }

        return codeToRole;
    }

    private void loadUsers(Map<String, Role> roles) {
        User admin = new User("admin", "admin", "Administrator", "admin.jpg");
        admin.setRoles(new ArrayList<>(Arrays.asList(roles.get("ROLE_ADMIN"))));

        User user = new User("user", "user", "Demo User", "user.jpg");
        user.setRoles(new ArrayList<>(Arrays.asList(roles.get("ROLE_USER"))));

        userService.save(admin, admin.getPassword());
        userService.save(user, user.getPassword());
    }
}
