package com.kevinmilet.myfreezerapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kevinmilet.myfreezerapi.entity.FreezerType;

/**
 * @author kevin
 *
 */
@Repository
public interface FreezerTypeRepository extends CrudRepository<FreezerType, Long> {

    @Transactional
    @Modifying
    @Query("update FreezerType ft set ft.name = :name where ft.id = :id")
    void updateFreezerType(@Param(value = "id") Long id, @Param(value = "name") String name);
}
