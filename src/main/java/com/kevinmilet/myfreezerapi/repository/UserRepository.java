package com.kevinmilet.myfreezerapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevinmilet.myfreezerapi.entity.User;

/**
 * @author kevin
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByEmail(String email);
}
