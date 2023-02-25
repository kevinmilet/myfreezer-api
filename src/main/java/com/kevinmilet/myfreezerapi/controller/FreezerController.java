package com.kevinmilet.myfreezerapi.controller;

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

import com.kevinmilet.myfreezerapi.entity.Freezer;
import com.kevinmilet.myfreezerapi.repository.FreezerRepository;

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

    @GetMapping(value = "/congelateurs")
    public ResponseEntity<List<Freezer>> getAllFreezer() {

	List<Freezer> freezers = (List<Freezer>) freezerRepository.findAll();

	return new ResponseEntity<>(freezers, HttpStatus.OK);
    }

    @GetMapping("/mes_congelateurs")
    public ResponseEntity<List<Freezer>> getFreezerByUser() {

	List<Freezer> freezerList = freezerRepository.findFreezerByUserId(USER_ID);

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
    public ResponseEntity<Freezer> createFreezer(@Valid @RequestBody Freezer freezer) {

//	return FreezerDto
//		.fromEntity(freezerService.createFreezer(FreezerDto.toEntity(freezerDto), principal).getBody());

	// TODO persist

	return new ResponseEntity<>(freezer, HttpStatus.CREATED);
    }

    @PutMapping("/congelateur/update/{id}")
    public ResponseEntity<Freezer> updateFreezer(@Valid @PathVariable("id") String id, @RequestBody Freezer freezer) {

//	if (id == null) {
//	    log.error("L'ID du congélateur est null");
//	    return null;
//	}
//
//	if (freezerDto == null) {
//	    log.error("Le congélateur est inconnu");
//	    return null;
//	}
//
//	return freezerService.updateFreezer(Long.parseLong(id), FreezerDto.toEntity(freezerDto));

	// TODO update depuis freezerId

	return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/congelateur/delete/{id}")
    public ResponseEntity<Object> deleteFreezer(@PathVariable("id") String id) {
//	freezerService.deleteFreezer(Long.parseLong(id));
//	return new ResponseEntity<>("Congélateur supprimé avec succes", HttpStatus.OK);

	// TODO delete depuis freezerId

	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}
