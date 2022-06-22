package nl.springboot.safar.services;

import nl.springboot.safar.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
	public List<User> findAll();

	public Optional<User> findById(Integer id);

	public User save(User user);

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String email);

//	@Override
//	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;



	//	public void deleteById(Integer id);
}
