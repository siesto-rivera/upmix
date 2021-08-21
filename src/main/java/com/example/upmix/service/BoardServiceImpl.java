package com.example.upmix.service;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.Member;
import com.example.upmix.domain.dto.BoardDTO;
import com.example.upmix.domain.dto.PageRequestDTO;
import com.example.upmix.domain.dto.PageResultDTO;
import com.example.upmix.repository.BoardRepository;
import com.example.upmix.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private  final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    ///  register
    @Override
    public  Long register(BoardDTO boardDTO){

        log.info(boardDTO);

        Board board = dtoToEntity(boardDTO);

        boardRepository.save(board);

        return board.getBno();
    }


    /// getList
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequstDTO) {

        log.info(pageRequstDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member) en[1], (Long) en[2]) );

        //Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequstDTO.getPageable(Sort.by("bno").descending()));

        Page<Object[]> result = boardRepository.searchPage(
                pageRequstDTO.getType(),
                pageRequstDTO.getKeyword(),
                pageRequstDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result,fn);

    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;

        return  entityToDTO((Board)arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);


    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getOne(boardDTO.getBno());

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }
}
