package com.example.upmix.meeting;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/News")
@Log4j2
class NewsController {
    @GetMapping("")
    public String list() {

        return "/news/list";
    }

    @GetMapping("/write")
    public String write() {
        return "/news/write";
    }
    @GetMapping({"/read", "/modify"})
    public String read() {
        return "/news/read";

    }
}

