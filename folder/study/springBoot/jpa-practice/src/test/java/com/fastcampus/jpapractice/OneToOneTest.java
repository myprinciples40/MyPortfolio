package com.fastcampus.jpapractice;

import com.fastcampus.jpapractice.repository.CartRepository;
import com.fastcampus.jpapractice.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Purpose: JPQL query method test
 * Features: Add some JPQL query method in the BoardRepository
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-17
 * Modification Date:
 */

@SpringBootTest
class OneToOneTest {
    @Autowired
    public EntityManager em;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private CartRepository cartRepo;

    @Test
    public void OneToOneTest() {
        Member member = new Member();
        member.setId(1L);
        member.setName("member1");
        member.setEmail("abc@aaa.com");
        member.setPassword("1111");
        memberRepo.save(member);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setMember(member);
        cartRepo.save(cart);

        cart = cartRepo.findById(cart.getId()).orElse(null);
        assertTrue(cart != null);
        System.out.println("cart = " + cart);

        member = memberRepo.findById(member.getId()).orElse(null);
        System.out.println("member = " + member);
        assertTrue(member != null);
    }
}
