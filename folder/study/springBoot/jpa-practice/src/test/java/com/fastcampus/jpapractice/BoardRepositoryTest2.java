package com.fastcampus.jpapractice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Purpose: JPQL query method test
 * Features: Add some JPQL query method in the BoardRepository
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-15
 * Modification Date:
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest2 {
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
    @Order(3)
    public void deleteTest() {
        assertTrue(boardRepo.deleteByWriter("writer1") == 20);
        List<Board> list = boardRepo.findByWriter("writer1");
        assertTrue(list.size() == 0);
    }

    @Test
    @Order(2)
    public void findTest() {
        List<Board> list = boardRepo.findByWriter("writer1");
        assertTrue(list.size() == 20);
        list.forEach(System.out::println);
    }

    @Test
    @Order(1)
    public void countTest() {
        assertTrue(boardRepo.countAllByWriter("writer1") == 20);
    }
}
