//package com.fastcampus.jpapractice;
//
//import com.fastcampus.jpapractice.repository.BoardRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
///**
// * Purpose: JPQL query method test
// * Features: Add some JPQL query method in the BoardRepository
// *
// * Author: Jinhwan Kim (Jin)
// * Date created: 2023-07-15
// * Modification Date:
// */
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class BoardRepositoryTest3 {
//    @Autowired
//    public EntityManager em;
//    @Autowired
//    private BoardRepository boardRepo;
//
//    @BeforeEach
//    public void testData() {
//        for (int i = 1; i <= 100; i++) {
//            Board board = new Board();
//            board.setBno((long)i);
//            board.setTitle("title" + i);
//            board.setContent("content" + i);
//            board.setWriter("writer" + (i % 5)); // writer0-4
//            board.setViewCnt((long)(Math.random() * 100)); // 0-99
//            board.setInDate(new Date());
//            board.setUpDate(new Date());
//            boardRepo.save(board);
//        }
//    }
//
//    @Test
//    @DisplayName("create native query test using @Query")
//    public void queryAnnoTest5() {
//        // @Query(value = "SELECT * FROM BOARD", nativeQuery = true) // SQL
//        // List<Board> findAllBoardBySQL();
//        List<Object[]> list = boardRepo.findAllBoardBySQL2();
//        list.stream() // Convert list to stream
//                .map(arr -> Arrays.toString(arr)) // Convert arr to String
//                .forEach(System.out::println);
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("create native query test using @Query")
//    public void queryAnnoTest4() {
//        // @Query(value = "SELECT * FROM BOARD", nativeQuery = true) // SQL
//        // List<Board> findAllBoardBySQL();
//        List<Board> list = boardRepo.findAllBoardBySQL();
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("create JPQL test using @Query - Parameter name")
//    public void queryAnnoTest3() {
//        // @Query("SELECT b FROM Board b WHERE b.title=:title AND b.writer=:writer")
//        // List<Board> findByTitleAndWriter2(String title, String writer);
//        List<Board> list = boardRepo.findByTitleAndWriter2("title1", "writer1");
//        list.forEach(System.out::println);
//        assertTrue(list.size() == 1);
//    }
//
//    @Test
//    @DisplayName("create JPQL test using @Query - Parameter order")
//    public void queryAnnoTest2() {
//        // @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
//        List<Board> list = boardRepo.findByTitleAndWriter2("title1", "writer1");
//        list.forEach(System.out::println);
//        assertTrue(list.size() == 1);
//    }
//
//    @Test
//    @DisplayName("create JPQL test using @Query")
//    public void queryAnnoTest() {
//        List<Board> list = boardRepo.findAllBoard();
//        assertTrue(list.size() == 100);
//    }
//
//    @Test
//    @DisplayName("create JPQL test using createQuery")
//    public void createQueryTest() {
//        String query = "SELECT b FROM Board b";
//        TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
//        List<Board> list = tQuery.getResultList();
//
//        list.forEach(System.out::println);
//        assertTrue(list.size() == 100);
//    }
//}
