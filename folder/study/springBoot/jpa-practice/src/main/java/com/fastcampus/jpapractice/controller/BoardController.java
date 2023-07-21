package com.fastcampus.jpapractice.controller;

import com.fastcampus.jpapractice.Board;
import com.fastcampus.jpapractice.User;
import com.fastcampus.jpapractice.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("/uploadForm")
    public String showUploadForm() {
        return "board/uploadFile";
    }

    @PostMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(String filename) {
        System.out.println("filename = " + filename);

        File file = new File(uploadPath + filename);
        if (file.delete() == true)
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(String filename) {
        System.out.println("fileName = " + filename);
        Resource resource = new FileSystemResource(uploadPath + filename);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    //    @PostMapping("/uploadFile")
    @ResponseBody
    @PostMapping(value = "/uploadAjax", headers = "Content-Type=multipart/form-data")
    public ResponseEntity<List<String>> uploadFile(@RequestParam(value = "files") MultipartFile[] files) throws IOException {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
            System.out.println("file.getSize(): " + file.getSize());

            File upFile = new File(uploadPath, file.getOriginalFilename());
            file.transferTo(upFile); // Save the uploaded file to the C:/upload folder
            list.add(file.getOriginalFilename());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/modify")
    public String modify(Long bno, Model model) {
        Board board = boardService.read(bno);
        model.addAttribute("board", board);
        return "board/write";
    }

    @PostMapping("/modify")
    public String modify(Board board) {
        boardService.modify(board);
        return "redirect:/board/list";
    }

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
