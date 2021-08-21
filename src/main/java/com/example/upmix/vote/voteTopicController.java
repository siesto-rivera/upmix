package com.example.upmix.vote;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/VoteTopics")
@Log4j2
public class voteTopicController {

    @GetMapping({"/", "/list"})
    public String list() {
        log.info("vote................");
        return "/vote/topic_list";
    }
}
