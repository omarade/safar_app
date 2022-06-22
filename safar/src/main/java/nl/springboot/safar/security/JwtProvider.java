package nl.springboot.safar.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

	private Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

	public String generateAccessJWT(HttpServletRequest request, HttpServletResponse response, UserDetails user){

		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);

		response.setHeader("access_token", accessToken);

		return accessToken;
	}

	public String generateRefreshJWT(HttpServletRequest request, HttpServletResponse response, UserDetails user) {

		String refreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);

		response.setHeader("refresh_token", refreshToken);

		return refreshToken;
	}
}
