/**
 * 
 */
package com.kevinmilet.myfreezerapi.service;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kevinmilet.myfreezerapi.entity.Freezer;

/**
 * @author kevin
 *
 */
public interface FreezerService {

    Iterable<Freezer> getAllFreezer();

    List<Freezer> getFreezerUser(Principal principal);

    ResponseEntity<Freezer> createFreezer(Freezer freezer, Principal principal);

    Freezer updateFreezer(Long id, Freezer freezer) throws Exception;

    void deleteFreezer(Long id) throws Exception;

    Freezer getFreezerById(Long id);
}
