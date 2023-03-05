/**
 * 
 */
package com.kevinmilet.myfreezerapi.service;

import java.util.List;

import com.kevinmilet.myfreezerapi.entity.User;

/**
 * @author kevin
 *
 */
public interface AdminService {

    List<User> getAllUsers();

    User updateUser(Long id, User userDto);

    void deleteUser(Long id);

    User getUserById(Long id);

    User findUserByEmail(String email);

    User activateUser(Long id, User user);

    User setUserAsAdmin(Long id, User user);
}
