package com.fastcampus.jpapractice.service;

import com.fastcampus.jpapractice.Board;
import com.fastcampus.jpapractice.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    // Method to get the whole post
    public List<Board> getList() {
        return (List<Board>) boardRepository.findAll();
    }

    // Method to write the post
    public Board write(Board board) {
        return boardRepository.save(board);
    }

    // Method to read the post
    public Board read(Long bno) {
        return boardRepository.findById(bno).orElse(null);
    }

    // Method to revise the post
    public Board modify(Board newBoard) {
        Board board = boardRepository.findById(newBoard.getBno()).orElse(null);

        if (board == null) return null;
        board.setTitle(newBoard.getTitle());
        board.setContent(newBoard.getContent());
        return boardRepository.save(board);
    }

    // Method to delete the post
    public void remove(Long bno) {
        Board board = boardRepository.findById(bno).orElse(null);
        if (board != null) {
            boardRepository.deleteById(bno);
        }
    }
}
