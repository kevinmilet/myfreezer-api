package com.kevinmilet.myfreezerapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.entity.User;

import jakarta.validation.Valid;

/**
 * @author kevin
 *
 */
@RestController
public class UserController {

    @PostMapping("/creer_utilisateur")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

	User newUser = new User();
	newUser.setEmail("toto@toto.com");
	newUser.setFirstname("Toto");
	newUser.setLastname("Toto");
	newUser.setIsActive(true);
	newUser.setIsAdmin(false);
	newUser.setAccountId("d15fe5sd14gv5sr4rh14g48edrh");
	newUser.setId(1L);

	return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

	User newUser = new User();
	newUser.setEmail("toto@toto.com");
	newUser.setFirstname("Toto");
	newUser.setLastname("Toto");
	newUser.setIsActive(true);
	newUser.setIsAdmin(false);
	newUser.setAccountId("d15fe5sd14gv5sr4rh14g48edrh");
	newUser.setId(1L);

	return new ResponseEntity<>(Arrays.asList(newUser), HttpStatus.OK);
    }

//    private final UserService userService;
//    private final ModelMapper modelMapper;
//    // private final JwtController jwtController;
//    // private final JwtUtils jwtUtils;
//
//    @Autowired
//    public UserController(UserService userService, ModelMapper modelMapper) {
//	this.userService = userService;
//	this.modelMapper = modelMapper;
//	// this.jwtController = jwtController;
//	// this.jwtUtils = jwtUtils;
//    }
//
//    @PostMapping("/creer_utilisateur")
//    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
//
//	UserDto userExiste = userService.findUserByEmail(userDto.getEmail());
//
//	if (userExiste != null) {
//	    return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
//	}
//
//	// convert DTO to entity
//	User request = modelMapper.map(userDto, User.class);
//
//	UserDto user = userService.createUser(request);
//
//	// convert entity to DTO
//	UserDto response = modelMapper.map(user, UserDto.class);

//        Authentication authentication = jwtController.logUser(utilisateurDto.getEmail(), utilisateurDto.getPassword());
//        String jwt = jwtUtils.generateToken(authentication);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//
//        return new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
    // return null;
//    }

//    @GetMapping(value = "/isConnected")
//    public ResponseEntity getUSerConnected() {
//	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	if (principal instanceof UserDetails) {
//	    return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
//	}
//	return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
//    }
}
