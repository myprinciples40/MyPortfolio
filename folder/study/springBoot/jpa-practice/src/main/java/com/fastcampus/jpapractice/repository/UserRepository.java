package com.fastcampus.jpapractice.repository;


import com.fastcampus.jpapractice.User;
import org.springframework.data.repository.CrudRepository;


/**
 * Purpose: Mapping associations
 * Features: 1:n associations
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-18
 * Modification Date:
 */
public interface UserRepository extends CrudRepository<User, String> {
}