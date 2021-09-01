package com.example.upmix.vote;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Vote")
@Log4j2
public class VoteController {

    @GetMapping("/")
    public String vote() {
        log.info("vote................");
        return "/vote/vote";
    }

    @GetMapping("/Topics")
    public String list() {
        log.info("vote................");
        return "/vote/topic_list";
    }

    @GetMapping("/Topics/read")
    public String topic_read() {

        return "/vote/topic_read";
    }

    @GetMapping("/Topics/write")
    public String topic_write() {

        return "/vote/topic_write";
    }
}
