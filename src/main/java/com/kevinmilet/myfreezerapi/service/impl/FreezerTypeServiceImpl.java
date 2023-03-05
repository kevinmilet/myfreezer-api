/**
 * 
 */
package com.kevinmilet.myfreezerapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kevinmilet.myfreezerapi.entity.FreezerType;
import com.kevinmilet.myfreezerapi.service.FreezerTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Service
@Slf4j
public class FreezerTypeServiceImpl implements FreezerTypeService {
    @Override
    public List<FreezerType> getAllFreezerTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public FreezerType createFreezerType(FreezerType freezerType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public FreezerType updateFreezerType(Long id, FreezerType freezerType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteFreezerType(Long Id) {
	// TODO Auto-generated method stub

    }

    @Override
    public FreezerType getFreezerType(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

}
