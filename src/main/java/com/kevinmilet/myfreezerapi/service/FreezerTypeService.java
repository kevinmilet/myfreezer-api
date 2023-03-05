/**
 * 
 */
package com.kevinmilet.myfreezerapi.service;

import java.util.List;

import com.kevinmilet.myfreezerapi.entity.FreezerType;

/**
 * @author kevin
 *
 */
public interface FreezerTypeService {

    List<FreezerType> getAllFreezerTypes();

    FreezerType createFreezerType(FreezerType freezerType);

    FreezerType updateFreezerType(Long id, FreezerType freezerType);

    void deleteFreezerType(Long Id);

    FreezerType getFreezerType(Long id);
}
