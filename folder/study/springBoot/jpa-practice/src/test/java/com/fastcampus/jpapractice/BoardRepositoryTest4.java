package com.fastcampus.jpapractice;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import static com.fastcampus.jpapractice.QBoard.board;

/**
 * Purpose: JPQL query method test
 * Features: Add some JPQL query method in the BoardRepository
 *
 * Author: Jinhwan Kim (Jin)
 * Date created: 2023-07-17
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

        // 1. Generate JPAQueryFactory
        JPAQueryFactory qf = new JPAQueryFactory(em);

        // 2. Write a query
        JPAQuery<Board> query = qf.selectFrom(board)
                .where(board.title.eq("title1"));

        // 3. Run a query
        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("Test writing queries with querydsl2 - Write complex queries")
    public void querydslTest2() {
        JPAQueryFactory qf = new JPAQueryFactory(em);

        JPAQuery<Tuple> query = qf.select(board.writer, board.viewCnt.sum()).from(board)
                .where(board.title.notLike("title1%"))
                .where(board.writer.eq("writer1"))
                .where(board.content.contains("content"))
                .where(board.content.isNotNull())
                .groupBy(board.writer)
                .having(board.viewCnt.sum().gt(100)) // Posts with more than 100 views
                .orderBy(board.writer.asc())
                .orderBy(board.viewCnt.sum().desc());

        // 3. Run a query
        List<Tuple> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("Test writing queries with querydsl3 - Write dynamic queries")
    public void querydslTest3() {
        String searchBy = "TC"; // search in title and content
        String keyword = "1";
        keyword = "%" + keyword + "%";

        BooleanBuilder builder = new BooleanBuilder();

        // Dynamically vary conditions
        if (searchBy.equalsIgnoreCase("T")) {
            builder.and(board.title.like(keyword));
        } else if (searchBy.equalsIgnoreCase("C")) {
            builder.and(board.content.like(keyword));
        } else if (searchBy.equalsIgnoreCase("TC")) {
            builder.and(board.title.like(keyword)).or(board.content.like(keyword));
        }

        JPAQueryFactory qf = new JPAQueryFactory(em);
        JPAQuery query = qf.selectFrom(board)
                .where(builder)
                .orderBy(board.upDate.desc());

        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }
}
