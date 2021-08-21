package com.example.upmix.repository;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private  BoardRepository boardRepository;

    @Test
    public void  게시판에_쓰기(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().email("user"+i +"@naver.com").build();

            Board board = Board.builder()
                    .title("제목..."+i)
                    .content("내용..."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void 게시판_읽기() {
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void 조인_게시글_저자() {

        Object result = boardRepository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;

        System.out.println("------------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void 조인_게시글_댓글(){

        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        System.out.println("------------------------------------");

        for (Object[] arr : result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void 게시판_댓글카운트(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row->{

            Object[] arr = (Object[]) row;

            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void 게시글_읽기() {

        Object result = boardRepository.getBoardByBno(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void Search1_테스트() {
        boardRepository.search1();
    }

    @Test
    public void testSearchPage() {

        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("bno").descending().and(Sort.by("title").ascending()));

        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }




}