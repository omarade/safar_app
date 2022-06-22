package nl.springboot.safar.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.springboot.safar.models.Site;
import nl.springboot.safar.models.User;
import nl.springboot.safar.models.dto.UserFavoriteSitesDto;
import nl.springboot.safar.security.JwtProvider;
import nl.springboot.safar.services.SiteService;
import nl.springboot.safar.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final SiteService siteService;
    private final JwtProvider jwtProvider;


    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = null;
        users = userService.findAll();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> GetUser(@PathVariable(value = "username") String username) {
        Optional<User> user = userService.findByUsername(username);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/favorites")
    public ResponseEntity<UserFavoriteSitesDto> getFavoriteSites(@PathVariable(value = "username") String username){
        Optional<User> user = userService.findByUsername(username);

        if(user.isPresent()) {
            UserFavoriteSitesDto userFavoriteSitesDto = new UserFavoriteSitesDto(user.get().getSites());
            return ResponseEntity.ok().body(userFavoriteSitesDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/favorites/{siteId}")
    public ResponseEntity toggleFavoriteSites(@PathVariable(value = "username") String username, @PathVariable(value = "siteId") Integer siteId){
        Optional<User> user = userService.findByUsername(username);
        Optional<Site> site = siteService.findById(siteId);

        boolean isFavorite = false;
        if(site.isPresent()){
            isFavorite = user.get().getSites().contains(site.get());
        }

        if (isFavorite) {
//            user.get().removeSite(site.get());
            user.get().getSites().remove(site.get());
        } else {
//            user.get().addSite(site.get());
            user.get().getSites().add(site.get());
        }

        userService.save(user.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> CreateUser(@RequestBody User user){
        User createdUser = userService.save(user);
        URI location = URI.create(String.format("/users/" + createdUser.getId()));
        return ResponseEntity.created(location).build();
    }

//    @GetMapping("/refresh-token")
//    public void RefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//
//        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
//            try {
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//
//                UserDetails user = userService.loadUserByUsername(username);
//
//                String access_token = jwtProvider.generateAccessJWT(request, response, user);
//
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//
//            } catch (Exception exception) {
//                log.error("Error signing in: {}", exception.getMessage());
//                response.setHeader("error", exception.getMessage());
//                //response.sendError(FORBIDDEN.value());
//                response.setStatus(FORBIDDEN.value());
//
//                Map<String, String> error = new HashMap<>();
//                error.put("error_message", exception.getMessage());
//                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//
//        } else {
//            throw new RuntimeException("Refresh token is missing");
//        }
//    }
}
