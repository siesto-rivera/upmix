package com.example.upmix.discussion;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DiscussionTopics")
@Log4j2
public class discussionTopicController {

    @GetMapping({"/", "/list"})
    public String list() {
        log.info("토론주제 게시판................");
        return "/discussion/topic_list";
    }
}
