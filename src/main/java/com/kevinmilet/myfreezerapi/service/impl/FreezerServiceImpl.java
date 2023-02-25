/**
 * 
 */
package com.kevinmilet.myfreezerapi.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kevinmilet.myfreezerapi.entity.Freezer;
import com.kevinmilet.myfreezerapi.repository.FreezerRepository;
import com.kevinmilet.myfreezerapi.service.FreezerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Service
@Slf4j
public class FreezerServiceImpl implements FreezerService {

    @Autowired
    private FreezerRepository freezerRepository;

    @Override
    public Iterable<Freezer> getAllFreezer() {
	return freezerRepository.findAll();
    }

    @Override
    public List<Freezer> getFreezerUser(Principal principal) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ResponseEntity<Freezer> createFreezer(Freezer freezer, Principal principal) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Freezer updateFreezer(Long id, Freezer freezer) throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteFreezer(Long id) throws Exception {
	// TODO Auto-generated method stub

    }

    @Override
    public Freezer getFreezerById(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

}
