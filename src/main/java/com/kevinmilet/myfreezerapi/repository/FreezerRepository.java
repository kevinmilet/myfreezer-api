package com.kevinmilet.myfreezerapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevinmilet.myfreezerapi.entity.Freezer;

/**
 * @author kevin
 *
 */
@Repository
public interface FreezerRepository extends CrudRepository<Freezer, Long> {

    List<Freezer> findFreezerByUserId(Long id);
}
