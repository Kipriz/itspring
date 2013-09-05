package itspring.services;

import itspring.domain.User;
import itspring.model.UserModel;
import itspring.repositories.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();

	@Mock
	private UserRepository userRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void should_throw_exception_when_user_not_found() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

		when(userRepositoryMock.findByLogin("testLogin")).thenReturn(null);
		// act
		userService.loadUserByUsername("testLogin");
	}

	@Test
	public void should_return_user_details() {
		// arrange
		User demoUser = new User("user", "demo", "test user");

        when(userRepositoryMock.findByLogin("user")).thenReturn(demoUser);

		// act
		UserDetails userDetails = userService.loadUserByUsername("user");

		// assert
		assertThat(demoUser.getLogin()).isEqualTo(userDetails.getUsername());
		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
	}
}
