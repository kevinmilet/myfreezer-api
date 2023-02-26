package com.kevinmilet.myfreezerapi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.jwt.JwtController;
import com.kevinmilet.myfreezerapi.jwt.JwtUtils;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtController jwtController;

    @Autowired
    private JwtUtils jwtUtils;

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
}
