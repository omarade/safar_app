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
	private final JwtProvider jwtProvider;

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
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), jwtProvider);
		customAuthenticationFilter.setFilterProcessesUrl("/authenticate");

		http.cors().and()
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

			.authorizeRequests()
			.antMatchers("/authenticate/**", "/register", "/refresh-token").permitAll()
			.antMatchers(HttpMethod.GET, "/sites", "users/{\\\\d+}/favorites", "users/{\\\\d+}/favorites/{\\\\d+}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
			.antMatchers(HttpMethod.POST, "/cities/**", "/sites/**").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.POST, "/users/**").permitAll()
			.antMatchers(HttpMethod.PUT, "/cities/**", "/sites/**", "/users/**").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated();
//				.exceptionHandling()
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


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
