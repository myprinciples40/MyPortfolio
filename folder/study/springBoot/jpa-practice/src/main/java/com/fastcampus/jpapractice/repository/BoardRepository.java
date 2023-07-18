package com.fastcampus.jpapractice.repository;

import com.fastcampus.jpapractice.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Purpose: JPQL query method test
 * Features: Add some JPQL query method in the BoardRepository
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-14
 * Modification Date: 2023-07-15
 */

public interface BoardRepository extends CrudRepository<Board, Long> {
    @Query("SELECT b FROM Board b") // JPQL is case sensitive for names
    List<Board> findAllBoard(); // Method names don't matter.

    @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
    List<Board> findByTitleAndWriter2(String title, String writer);

    // Use when running complex queries
    @Query(value = "SELECT * FROM BOARD", nativeQuery = true) // native SQL
    List<Board> findAllBoardBySQL();

    @Query(value = "SELECT TITLE, WRITER FROM BOARD", nativeQuery = true) // native SQL
    List<Object[]> findAllBoardBySQL2();

    // SELECT COUNT(*) FROM BOARD WHERE WRITER = :writer
    int countAllByWriter(String writer);

    // SELECT * FROM BOARD WHERE WRITER = :writer
    List<Board> findByWriter(String writer);

    // SELECT * FROM BOARD WHERE TITLE = :title AND WRITER = :writer
    List<Board> findByTitleAndWriter(String title, String writer);

    // DELETE FROM BOARD WHERE WRITER = : writer
    // For delete, Tx processing is required because multiple cases can be deleted.
    @Transactional
    int deleteByWriter(String writer);
}