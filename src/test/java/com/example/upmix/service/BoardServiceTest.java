package com.example.upmix.service;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.dto.BoardDTO;
import com.example.upmix.domain.dto.PageRequestDTO;
import com.example.upmix.domain.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private  BoardService boardService;

    @Test
    public void 게시판_글쓰기() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test...")
                .content("내용....")
                .writerEmail("user55@naver.com")
                .build();

        Long bno = boardService.register(boardDTO);
    }

    @Test
    public void 게시판_리스트() {

        PageRequestDTO pageRequstDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequstDTO);

        for (BoardDTO boardDTO: result.getDtoList()){
            System.out.println(boardDTO);
        }


    }
    @Test
    public void 게시글_읽기(){

        Long bno = 100L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void 게시글_삭제(){

        Long bno = 17L;

        boardService.removeWithReplies(bno);
    }

    @Test
    public void 게시글_수정() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("바뀐제목")
                .content("바뀐 내용")
                .build();

        boardService.modify(boardDTO);


    }

}