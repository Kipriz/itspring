package itspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import itspring.services.UserService;

import javax.annotation.PostConstruct;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
@PropertySource(value = "classpath:env.properties")
public class SecurityConfig {

    @Autowired
    Environment env;
    
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", userService());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder(env.getProperty("security.secret"));
	}
    
    @PostConstruct
    public void additionalSecuritySettings() {
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
    }
}