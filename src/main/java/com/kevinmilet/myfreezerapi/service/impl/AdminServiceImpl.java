/**
 * 
 */
package com.kevinmilet.myfreezerapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kevinmilet.myfreezerapi.entity.User;
import com.kevinmilet.myfreezerapi.service.AdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 *
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Override
    public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User updateUser(Long id, User userDto) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void deleteUser(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public User getUserById(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User findUserByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User activateUser(Long id, User user) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public User setUserAsAdmin(Long id, User user) {
	// TODO Auto-generated method stub
	return null;
    }

}
