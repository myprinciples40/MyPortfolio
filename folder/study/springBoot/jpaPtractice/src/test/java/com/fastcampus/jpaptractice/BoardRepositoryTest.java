package com.fastcampus.jpaptractice;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Purpose: CrudRepository test
 * Features: Verify that INSERT, SELECT, UPDATE, and DELETE all work well
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-14
 * Modification Date:
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepo;

    @Test
    @Order(4)
    public void deleteTest() {
        boardRepo.deleteById(1L); //Delete post 1

        Board board = boardRepo.findById(1L).orElse(null);
        assertTrue(board == null); //Must be null because it was deleted
    }

    @Test
    @Order(3)
    public void updateTest() {
        Board board = boardRepo.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("modified Title");
        boardRepo.save(board);
        //Return a new board object if not found
        Board board2 = boardRepo.findById(1L).orElse(new Board());
        System.out.println("board = " + board);
        System.out.println("board2 = " + board2);

        assertTrue(board.getTitle().equals(board2.getTitle()));
    }

    @Test
    @Order(2)
    public void selectTest() {
//        Board board = boardRepo.findById(1L).get(); //Throwing an exception when no value exists
        Board board = boardRepo.findById(1L).orElse(null); //Returning null when there is no value
        System.out.println("board = " + board);
        assertTrue(board != null);

    }

    @Test
    @Order(1)
    public void insertTest() {
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("Test Title");
        board.setContent("This is the Test");
        board.setWriter("aaa");
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepo.save(board);
    }
}