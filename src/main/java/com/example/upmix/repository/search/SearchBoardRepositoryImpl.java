package com.example.upmix.repository.search;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.QBoard;
import com.example.upmix.domain.QMember;
import com.example.upmix.domain.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements  SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1==========================================");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;


        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        //jpqlQuery.select(board, member.email, reply.count()).groupBy(board);
        //List<Board> result = jpqlQuery.fetch();
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(board,member.email,reply.count());
        tupleJPQLQuery.groupBy(board);

        log.info("-----------------------------------");
        log.info(tupleJPQLQuery);
        log.info("-----------------------------------");

        List<Tuple> result = tupleJPQLQuery.fetch();
        //log.info(result);

        return null;
    }

    /// searchPage
    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(board,member,reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
                booleanBuilder.and(conditionBuilder);
            }



        }

        tupleJPQLQuery.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        //tuple.orderBy(board.bno.desc());

        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
            tupleJPQLQuery.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));

        });

        tupleJPQLQuery.groupBy(board);

        //page 처리
        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());

        List<Tuple> result = tupleJPQLQuery.fetch();
        log.info(result);

        long count = tupleJPQLQuery.fetchCount();
        log.info("Count:" + count);

        return new PageImpl<Object[]>(
                result.stream().map(t->t.toArray()).collect(Collectors.toList()), pageable, count
        );

    }
}
