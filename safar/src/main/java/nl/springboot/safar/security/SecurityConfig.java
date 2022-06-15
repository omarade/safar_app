package nl.springboot.safar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/authenticate");

		http.cors().and()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
				.antMatchers("/authenticate/**", "/register").permitAll()
				.antMatchers(HttpMethod.GET, "/sites").permitAll()
				.antMatchers(HttpMethod.DELETE, "/cities/**", "/sites/**", "/users/**").hasAuthority("Admin")
				.antMatchers(HttpMethod.POST, "/cities/**", "/sites/**").hasAuthority("Admin")
				.antMatchers(HttpMethod.POST, "/users/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/cities/**", "/sites/**", "/users/**").hasAuthority("Admin")
				.antMatchers(HttpMethod.GET, "/users").hasAuthority("Admin")
				.anyRequest().authenticated();
//				.exceptionHandling()

//		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilter(customAuthenticationFilter);

//		http.csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(STATELESS);
//		http.authorizeRequests().anyRequest().permitAll();
//		http.addFilter(new CustomAuthFilter(authenticationManagerBean()));
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}