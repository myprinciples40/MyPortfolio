package com.fastcampus.jpapractice.service;

import com.fastcampus.jpapractice.Board;
import com.fastcampus.jpapractice.User;
import com.fastcampus.jpapractice.repository.BoardRepository;
import com.fastcampus.jpapractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        for (long i  = 1L; i  <= 10; i ++) {
            Board board = new Board();
            board.setBno(i);
            board.setTitle("Title" + i);
            board.setContent("Content..." + i);
            User user = new User();
            user.setId("111");
            userRepository.save(user);

            board.setUser(user);
            board.setViewCnt(0L);
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardService.write(board);
        }
    }

    @DisplayName("Test to get the list")
    @Test
    public void getListTest() {
        List<Board> list = boardService.getList();
        System.out.println("list = " + list);
        assertEquals(list.size(), 10);
    }

    @DisplayName("Test to write and read the post")
    @Test
    public void writeAndReadTest() {
        User user = new User();
        user.setId("bbb");
        userRepository.save(user);

        Board board = new Board();
        board.setBno(11L);
        board.setTitle("new Title");
        board.setContent("new Content");
        board.setUser(user);
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardService.write(board);

        Board board2 = boardService.read(11L);
        assertTrue(board2 != null);
        assertEquals(board.getTitle(), board2.getTitle());
        assertEquals(board.getContent(), board2.getContent());
    }

    @DisplayName("Test to modify the post")
    @Test
    public void modifyTest() {
        Board board = boardService.read(1L);
        board.setTitle("modified");
        board.setContent("modified Content");
        boardService.modify(board);

        Board board2 = boardService.read(1L);
        assertTrue(board2 != null);
        assertEquals(board.getTitle(), board2.getTitle());
        assertEquals(board.getContent(), board2.getContent());
    }

    @DisplayName("Test to delete the post")
    @Test
    public void removeTest() {
        Long testBno = 5L;

        assertTrue(boardService.read(testBno) != null);

        boardService.remove(testBno);
        assertEquals(boardService.read(testBno), null);
    }
}