package itspring.services;

import com.google.common.collect.Lists;
import itspring.domain.Role;
import itspring.domain.User;
import itspring.model.RoleModel;
import itspring.model.UserModel;
import itspring.repositories.RoleRepository;
import itspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

public class UserService implements UserDetailsService {
	
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageSource messageSource;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(messageSource.getMessage("errors.user_not_found", new Object[]{username}, null));
        }
        return user;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User save(User user, String password) {
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }
        return this.save(user);
    }

    @Transactional
    public User save(UserModel userModel) {
        User user;
        if (userModel.getId() == null || userModel.getId() == 0) {
            user = createNewUser(userModel);
        } else {
            user = updateUser(userModel);
        }
        user.setLastModifiedDate(new Date());
        return userRepository.save(user);
    }

    private User updateUser(UserModel userModel) {
        User fromDb = userRepository.findOne(userModel.getId());
        populateUserFromModel(fromDb, userModel);

        return fromDb;
    }

    private User createNewUser(UserModel userModel) {
        User user = new User();
        populateUserFromModel(user, userModel);
        return user;
    }

    private User populateUserFromModel(User user, UserModel userModel) {
        user.setLogin(userModel.getLogin());
        if (userModel.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }
        user.setName(userModel.getName());

        List<Role> newRoles = new ArrayList<>();
        for (RoleModel roleModel : userModel.getRoles()) {
            Role role = roleRepository.findOne(roleModel.getId());
            newRoles.add(role);
        }

        user.setRoles(newRoles);

        return user;
    }

    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    public User disableUser(Long userId) {
        User user = userRepository.findOne(userId);
        user.setEnabled(false);
        return save(user);
    }

    public User enableUser(Long userId) {
        User user = userRepository.findOne(userId);
        user.setEnabled(true);
        return save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
