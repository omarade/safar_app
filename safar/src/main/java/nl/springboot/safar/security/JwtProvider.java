package nl.springboot.safar.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtProvider<T> {
//	public static generateJWT(String username){
//		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//
//		String accessToken = JWT.create()
//				.withSubject(user.getUsername())
//				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//				.withIssuer(request.getRequestURI().toString())
//				.withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//				.sign(algorithm);
//
//		String refreshToken = JWT.create()
//				.withSubject(user.getUsername())
//				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//				.withIssuer(request.getRequestURI().toString())
//				.withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//				.sign(algorithm);

//		response.setHeader("access_token", accessToken);
//		response.setHeader("refresh_token", refreshToken);

//		Map<String, String> tokens = new HashMap<>();
//		tokens.put("access_token", accessToken);
//		tokens.put("refresh_token", refreshToken);
//	}
}
