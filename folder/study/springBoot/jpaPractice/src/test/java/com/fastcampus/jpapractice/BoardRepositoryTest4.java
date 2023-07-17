package com.fastcampus.jpapractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Date;


/**
 * Purpose: JPQL query method test
 * Features: Add some JPQL query method in the BoardRepository
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-16
 * Modification Date:
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest4 {
    @Autowired
    public EntityManager em;
    @Autowired
    private BoardRepository boardRepo;

    @BeforeEach
    public void testData() {
        for (int i = 1; i <= 100; i++) {
            Board board = new Board();
            board.setBno((long)i);
            board.setTitle("title" + i);
            board.setContent("content" + i);
            board.setWriter("writer" + (i % 5)); // writer0-4
            board.setViewCnt((long)(Math.random() * 100)); // 0-99
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardRepo.save(board);
        }
    }

    @Test
    @DisplayName("Test writing queries with querydsl1 - Write a simple query")
    public void querydslTest() {
//        QBoard board = QBoard.board;
    }

}
