package com.fastcampus.jpapractice.controller;

import com.fastcampus.jpapractice.Board;
import com.fastcampus.jpapractice.User;
import com.fastcampus.jpapractice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        Board board = new Board();
        User user = new User();
        user.setId("bbb");
        board.setUser(user);

        model.addAttribute("board", board);
        return "/board/write";
    }

    @PostMapping("/write")
    public String write(Board board) {
//        board.setBno(11L); // Preferably with auto-increment
        User user = new User();
        user.setId("bbb");
        board.setUser(user);
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());

        boardService.write(board);
        return "redirect:/board/list"; // After deleting a post, go to the list of posts
    }

    @PostMapping("/remove")
    public String remove(Long bno) {
        boardService.remove(bno);
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Long bno, Model model) {
        Board board = boardService.read(bno);
        model.addAttribute("board", board);
        return "/board/read";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<Board> list = boardService.getList();
        model.addAttribute("list", list);

        return "/board/list";
    }
}
