package com.fastcampus.jpapractice.repository;

import com.fastcampus.jpapractice.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Purpose: Mapping associations
 * Features: 1:1 and 1:N associations
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-18
 * Modification Date:
 */

public interface CartRepository extends CrudRepository<Cart, Long> {

}