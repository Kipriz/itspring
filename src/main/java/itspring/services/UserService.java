package itspring.services;

import com.google.common.collect.Lists;
import itspring.domain.User;
import itspring.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserService implements UserDetailsService {
	
    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    public itspring.domain.User save(itspring.domain.User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }
}
