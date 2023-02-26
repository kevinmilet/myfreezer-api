package com.kevinmilet.myfreezerapi.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
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
    @PostMapping("/add-user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {

	User existingUser = userRepository.findOneByEmail(user.getEmail());

	if (existingUser != null) {
	    log.error("Un utilisateur est déjà enregistré avec cet adresse email");
	    return new ResponseEntity("Un utilisateur est déjà enregistré avec cet adresse email",
		    HttpStatus.BAD_REQUEST);
	}

	User newUser = saveUser(user);
	Authentication authentication = jwtController.logUser(user.getEmail(), user.getPassword());
	String jwt = jwtUtils.generateToken(authentication);
	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

	return new ResponseEntity<>(newUser, httpHeaders, HttpStatus.OK);
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

}
