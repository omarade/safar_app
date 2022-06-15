package nl.springboot.safar.services;

import nl.springboot.safar.models.User;
import nl.springboot.safar.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.mockito.Mockito.when;

@ContextConfiguration
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;


	@Test
	public void getAllUsers () {
		List<User> expectedUsers = Arrays.asList(
				new User(1, "user1", "address","user1","email", "lsdkhfkjsdhfk", true, false),
				new User(2, "user2", "address","user1","email", "lsdkhfkjsdhfk", true, false),
				new User(3, "user3", "address","user1","email", "lsdkhfkjsdhfk", true, false)
		);
		when(userRepository.findAll()).thenReturn(
				expectedUsers
		);

		List<User> actualUsers = userService.findAll();

		Assertions.assertEquals(expectedUsers, actualUsers);
		Assertions.assertEquals(expectedUsers.size(), actualUsers.size());
	}

	@Test
	public void getAllUsers_noUsers () {
		List<User> expectedUsers = new ArrayList<>();

		when(userRepository.findAll()).thenReturn(
				expectedUsers
		);

		List<User> actualUsers = userService.findAll();

		Assertions.assertEquals(expectedUsers, actualUsers);
		Assertions.assertEquals(expectedUsers.size(), actualUsers.size());
	}

	@Test
	public void getUserById () {
		Optional<User> expectedUser = Optional.of(new User(1, "user1", "address","user1","email", "lsdkhfkjsdhfk", true, false));

		when(userRepository.findById(1)).thenReturn(
				expectedUser
		);

		Optional<User> actualUser = userService.findById(1);

		Assertions.assertEquals(expectedUser, actualUser);
		Assertions.assertEquals(expectedUser.get().getId(), actualUser.get().getId());
		Assertions.assertEquals(expectedUser.get().getName(), actualUser.get().getName());
		Assertions.assertEquals(expectedUser.get().getUsername(), actualUser.get().getUsername());
		Assertions.assertEquals(expectedUser.get().getAddress(), actualUser.get().getAddress());
		Assertions.assertEquals(expectedUser.get().getEmail(), actualUser.get().getEmail());
	}

	@Test
	public void getUserById_noUser () {
		Optional<User> expectedUser = Optional.empty();

		when(userRepository.findById(1)).thenReturn(
				expectedUser
		);

		Optional<User> actualUser = userService.findById(1);

		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	public void createUser () {
		User user = new User(1, "user1", "address","user1","email", "password", true, false);

		when(userRepository.save(user)).thenReturn(
				user
		);

		when(passwordEncoder.encode("password")).thenReturn(
				"encodedPass"
		);

		User actualUser = userService.save(user);

		Assertions.assertEquals(user, actualUser);
	}

	@Test
	public void getUserByUsername() {
		String username = "username1";
		Optional<User> user = Optional.of(new User(1, "user1", "address", username,"email", "password", true, false));

		when(userRepository.findByUsername( username )).thenReturn( user );

		Optional<User> actualUser = userService.findByUsername(username);

		Assertions.assertEquals(user, actualUser);
	}

	@Test
	public void getUserByEmail() {
		String email = "email1";
		Optional<User> user = Optional.of(new User(1, "user1", "address","username", email, "password", true, false));

		when(userRepository.findByEmail( email )).thenReturn( user );

		Optional<User> actualUser = userService.findByEmail(email);

		Assertions.assertEquals(user, actualUser);
	}

	@Test
	public void loadUserByUsername() {
		Optional<User> user = Optional.of(new User(1, "user1", "address","username", "email", "password", true, false));

		when(userService.findByUsername(user.get().getUsername())).thenReturn( user	);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("Admin"));
		org.springframework.security.core.userdetails.User expectedUser = new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);

		org.springframework.security.core.userdetails.UserDetails actualUser = userService.loadUserByUsername(user.get().getUsername());

		Assertions.assertEquals(expectedUser, actualUser);
	}

}
