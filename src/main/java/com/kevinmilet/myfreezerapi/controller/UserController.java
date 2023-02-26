package com.kevinmilet.myfreezerapi.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.common.Utils;
import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.jwt.JwtController;
import com.kevinmilet.myfreezerapi.jwt.JwtFilter;
import com.kevinmilet.myfreezerapi.jwt.JwtUtils;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtController jwtController;

    @Autowired
    private JwtUtils jwtUtils;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/creer_utilisateur")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

	User existingUser = userRepository.findOneByEmail(user.getEmail());

	if (existingUser != null) {
	    log.error("Un utilisateur est déjà enregistré avec cet adresse email");
	    return new ResponseEntity("Un utilisateur est déjà enregistré avec cet adresse email",
		    HttpStatus.BAD_REQUEST);
	}

	User savedUser = saveUser(user);

	Authentication authentication = jwtController.logUser(user.getEmail(), user.getPassword());
	String jwt = jwtUtils.generateToken(authentication);
	HttpHeaders headers = new HttpHeaders();
	headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

	return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    private User saveUser(User user) {

	User newUser = new User();
	newUser.setEmail(user.getEmail().trim());
	newUser.setFirstname(StringUtils.capitalize(user.getFirstname().trim()));
	newUser.setLastname(StringUtils.capitalize(user.getLastname().trim()));
	newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	newUser.setIsActive(Boolean.FALSE);
	newUser.setIsAdmin(Boolean.FALSE);
	newUser.setCreated_at(Instant.now());
	newUser.setAccountId(Utils.generateUUID());

	userRepository.save(newUser);

	return newUser;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

	List<User> userList = (List<User>) userRepository.findAll();

	return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public Long getUserConnectedId(Principal principal) {
	if (!(principal instanceof UsernamePasswordAuthenticationToken)) {
	    throw new RuntimeException("User not found");
	}

	UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
	User oneByMail = userRepository.findOneByEmail(user.getName());

	return oneByMail.getId();
    }

}
