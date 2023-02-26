/**
 *
 */
package com.kevinmilet.myfreezerapi.controller;

import java.util.List;

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

import com.kevinmilet.myfreezerapi.entity.FreezerType;
import com.kevinmilet.myfreezerapi.repository.FreezerTypeRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@RestController
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class FreezerTypeController {

    @Autowired
    private FreezerTypeRepository freezerTypeRepository;

    @GetMapping("/types_congelateurs")
    public ResponseEntity<List<FreezerType>> getAllFreezerTypes() {

	List<FreezerType> freezerTypesList = (List<FreezerType>) freezerTypeRepository.findAll();

	return new ResponseEntity<>(freezerTypesList, HttpStatus.OK);
    }

    @PostMapping("/type_congelateur/create")
    public ResponseEntity<FreezerType> createFreezerType(@Valid @RequestBody FreezerType freezerType) {

	FreezerType newFreezerType = new FreezerType();
	newFreezerType.setName(freezerType.getName());

	freezerTypeRepository.save(newFreezerType);

	return new ResponseEntity<>(newFreezerType, HttpStatus.CREATED);
    }

    @DeleteMapping("/type_congelateur/delete/{id}")
    public ResponseEntity<Object> deleteFreezerType(@PathVariable("id") Long id) {

	if (id == null) {
	    log.error("type de congelateur inconnu");
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	FreezerType freezerType;

	try {
	    freezerType = freezerTypeRepository.findById(id).orElseThrow(Exception::new);
	    freezerTypeRepository.delete(freezerType);

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	} catch (Exception e) {
	    log.error("Type de congélateur non trouvé: " + e.getMessage());
	}

	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/type_congelateur/update/{id}")
    public ResponseEntity<FreezerType> updateProduct(@PathVariable("id") Long id,
	    @RequestBody FreezerType freezerTypeReq) {

	if (id == null) {
	    log.error("L'ID du type de congélateur est null");
	    return null;
	}

	if (freezerTypeReq == null) {
	    log.error("Le type de congélateur est inconnu");
	    return null;
	}

	FreezerType freezerType = freezerTypeRepository.findById(id).orElseThrow();

	freezerType.setName(freezerTypeReq.getName());

	freezerTypeRepository.save(freezerType);

	return new ResponseEntity<>(freezerType, HttpStatus.OK);
    }

}
