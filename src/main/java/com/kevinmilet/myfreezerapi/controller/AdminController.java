package com.kevinmilet.myfreezerapi.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * @author kevin
 *
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

	List<User> userList = (List<User>) userRepository.findAll();

	return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(name = "id") Long id) {

	Optional<User> user = userRepository.findById(id);
	return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/isConnected")
    public ResponseEntity<?> getUserConnected() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	if (principal instanceof UserDetails) {
	    return new ResponseEntity<>(((UserDetails) principal).getUsername(), HttpStatus.OK);
	}
	return new ResponseEntity<>("User not connected", HttpStatus.FORBIDDEN);
    }

    public Long getUserConnectedId(Principal principal) {
	if (!(principal instanceof UsernamePasswordAuthenticationToken)) {
	    throw new RuntimeException("User not found");
	}

	UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
	User oneByMail = userRepository.findOneByEmail(user.getName());

	return oneByMail.getId();
    }

    @PutMapping("/utilisateur/activation/{id}")
    public ResponseEntity<User> activateUser(@PathVariable(name = "id") Long id, @RequestBody User userReq) {
	User user = userRepository.findById(id).orElseThrow();

	Boolean isActive = userReq.getIsActive();

	if (Boolean.FALSE.equals(isActive)) {
	    user.setIsActive(Boolean.TRUE);
	} else {
	    user.setIsActive(Boolean.FALSE);
	}

	userRepository.save(user);

	return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/utilisateur/admin/{id}")
    public ResponseEntity<User> setUserAsAdmin(@PathVariable(name = "id") Long id, @RequestBody User userReq) {
	User user = userRepository.findById(id).orElseThrow();

	Boolean isAdmin = userReq.getIsAdmin();

	if (Boolean.FALSE.equals(isAdmin)) {
	    user.setIsAdmin(Boolean.TRUE);
	} else {
	    user.setIsAdmin(Boolean.FALSE);
	}

	userRepository.save(user);

	return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/utilisateur/update/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User userReq, Principal principal) {

	Long userId = getUserConnectedId(principal);
	User user = userRepository.findById(userId).orElseThrow();

	if (!userReq.getEmail().equals(user.getEmail())) {
	    user.setEmail(userReq.getEmail().trim());
	}
	if (!(userReq.getFirstname().toLowerCase()).equals(user.getFirstname().toLowerCase())) {
	    user.setFirstname(StringUtils.capitalize(userReq.getFirstname()).trim());
	}
	if (!(userReq.getLastname().toLowerCase()).equals(user.getLastname().toLowerCase())) {
	    user.setLastname(StringUtils.capitalize(userReq.getLastname()).trim());
	}
	if (!(new BCryptPasswordEncoder().matches(userReq.getPassword(), user.getPassword()))) {
	    user.setPassword(new BCryptPasswordEncoder().encode(userReq.getPassword()));
	}

	user.setUpdated_at(Instant.now());

	userRepository.save(user);

	return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
