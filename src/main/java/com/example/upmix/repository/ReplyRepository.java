package com.example.upmix.repository;

import com.example.upmix.domain.Board;
import com.example.upmix.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query(" delete from  Reply r where r.board.bno =:bno ")
    void deleteByBno(@Param("bno") Long bno);

    List<Reply> getRepliesByBoardOrderByRno(Board board);

}


