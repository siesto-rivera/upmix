package com.example.upmix.repository;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void 댓글쓰기() {

        IntStream.rangeClosed(1,300).forEach(i->{

            long bno = (long) (Math.random() *100) +1;
            if(bno == 1){
                bno += 1;
            }

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("댓글..." +i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    void 댓글리스트_byBoardId() {

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(
                Board.builder().bno(97L).build()
        );

        replyList.forEach(reply -> System.out.println(reply));
    }



}