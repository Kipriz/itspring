package itspring.services;

import com.google.common.collect.Lists;
import itspring.domain.User;
import itspring.model.UserModel;
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
    public User save(UserModel user) {
//        user.setLastModifiedDate(new Date());
        return null;
    }

    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }
}
