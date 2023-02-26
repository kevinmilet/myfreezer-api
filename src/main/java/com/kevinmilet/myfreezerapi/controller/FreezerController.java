package com.kevinmilet.myfreezerapi.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kevinmilet.myfreezerapi.common.Utils;
import com.kevinmilet.myfreezerapi.entity.Freezer;
import com.kevinmilet.myfreezerapi.entity.FreezerType;
import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.repository.FreezerRepository;
import com.kevinmilet.myfreezerapi.repository.FreezerTypeRepository;
import com.kevinmilet.myfreezerapi.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
public class FreezerController {

    private static final Long USER_ID = 1L;

    @Autowired
    private FreezerRepository freezerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FreezerTypeRepository freezerTypeRepository;
    @Autowired
    private UserController userController;

    @GetMapping(value = "/congelateurs")
    public ResponseEntity<List<Freezer>> getAllFreezer() {

	List<Freezer> freezers = (List<Freezer>) freezerRepository.findAll();

	return new ResponseEntity<>(freezers, HttpStatus.OK);
    }

    @GetMapping("/mes_congelateurs")
    public ResponseEntity<List<Freezer>> getFreezerByUser(Principal principal) {

	Long userId = userController.getUserConnectedId(principal);
	List<Freezer> freezerList = freezerRepository.findFreezerByUserId(userId);

	return new ResponseEntity<>(freezerList, HttpStatus.OK);

    }

    @GetMapping("/congelateur/{id}")
    public Optional<Freezer> getFreezerById(@PathVariable(name = "id") String id) {
	if (id == null) {
	    log.error("L'ID du congélateur est null");
	    return null;
	}

	return freezerRepository.findById((Long.parseLong(id)));
    }

    @PostMapping("/congelateur/create")
    public ResponseEntity<Freezer> createFreezer(@Valid @RequestBody Freezer freezer, Principal principal) {

	Long userId = userController.getUserConnectedId(principal);
	Optional<User> user = userRepository.findById(userId);
	Optional<FreezerType> freezerType = freezerTypeRepository.findById(freezer.getFreezerType().getId());
	String uuid = Utils.generateUUID();

	if (freezerType.isPresent()) {
	    freezer.setFreezerType(freezerType.get());
	} else {
	    return new ResponseEntity("Vous devez indiquer le type de congélateur", HttpStatus.BAD_REQUEST);
	}

	if (user.isPresent()) {
	    freezer.setUser(user.get());
	} else {
	    return new ResponseEntity("L'utilisateur n'a pas été trouvé", HttpStatus.BAD_REQUEST);
	}

	freezer.setFreezerId(uuid);
	freezer.setCreationDate(Instant.now());

	freezerRepository.save(freezer);

	return new ResponseEntity<>(freezer, HttpStatus.CREATED);
    }

    @PutMapping("/congelateur/update/{id}")
    public ResponseEntity<Freezer> updateFreezer(@Valid @PathVariable("id") Long id, @RequestBody Freezer freezerReq) {

	if (id == null) {
	    log.error("L'ID du congélateur est null");
	    return null;
	}

	if (freezerReq == null) {
	    log.error("Le congélateur est inconnu");
	    return null;
	}

	Freezer freezer = freezerRepository.findById(id).orElseThrow();

	freezer.setName(freezerReq.getName());
	freezer.setFreezerType(freezerReq.getFreezerType());
	freezer.setUpdateDate(Instant.now());

	freezerRepository.save(freezer);

	return new ResponseEntity<>(freezer, HttpStatus.OK);
    }

    @DeleteMapping("/congelateur/delete/{id}")
    public ResponseEntity<Object> deleteFreezer(@PathVariable("id") Long id) {
	if (id == null) {
	    log.error("congelateur inconnu");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	Freezer freezer;

	try {
	    freezer = freezerRepository.findById(id).orElseThrow(Exception::new);
	    freezerRepository.delete(freezer);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	} catch (Exception e) {
	    log.error("Congélateur non trouvé: " + e.getMessage());
	}

	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
