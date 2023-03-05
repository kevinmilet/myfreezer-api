package com.kevinmilet.myfreezerapi.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

/**
 * @author kevin
 *
 */
@RestController
public class JwtController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {
	Authentication authentication = logUser(jwtRequest.getEmail(), jwtRequest.getPassword());
	String jwt = jwtUtils.generateToken(authentication);

	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

	Object principal = authentication.getPrincipal();

	User user = userRepository.findOneByEmail(jwtRequest.getEmail());

	Boolean isAdmin = user.getIsAdmin();

	return new ResponseEntity<>(new JwtResponse(principal.toString(), isAdmin), httpHeaders, HttpStatus.OK);
    }

    public Authentication logUser(String email, String password) {
	Authentication authentication = authenticationManagerBuilder.getObject()
		.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	SecurityContextHolder.getContext().setAuthentication(authentication);

	return authentication;
    }
}
