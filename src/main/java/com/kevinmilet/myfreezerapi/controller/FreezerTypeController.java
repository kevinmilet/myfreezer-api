/**
 *
 */
package com.kevinmilet.myfreezerapi.controller;

import java.util.Arrays;
import java.util.List;

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

import jakarta.validation.Valid;

/**
 * @author kevin
 *
 */
@RestController
public class FreezerTypeController {

    @GetMapping("/types_congelateurs")
    public ResponseEntity<List<FreezerType>> getAllFreezerTypes() {

	FreezerType freezerType = new FreezerType();
	freezerType.setId(1L);
	freezerType.setName("Armoire");

	return new ResponseEntity<>(Arrays.asList(freezerType), HttpStatus.OK);
    }

    @PostMapping("/type_congelateur/create")
    public ResponseEntity<FreezerType> createFreezerType(@Valid @RequestBody FreezerType freezerType) {

	FreezerType newFreezerType = new FreezerType();
	newFreezerType.setId(1L);
	newFreezerType.setName("Armoire");

	return new ResponseEntity<>(newFreezerType, HttpStatus.CREATED);
    }

    @DeleteMapping("/type_congelateur/delete/{id}")
    public ResponseEntity<Object> deleteFreezerType(@PathVariable("id") String id) {

	// TODO delete depuis freezerId

	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/type_congelateur/update/{id}")
    public ResponseEntity<FreezerType> updateProduct(@PathVariable("id") String id,
	    @RequestBody FreezerType freezerType) {

	// TODO update depuis freezerId

	return new ResponseEntity<>(HttpStatus.OK);
    }

}
