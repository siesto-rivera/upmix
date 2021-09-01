package com.example.upmix.debate;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Debate")
@Log4j2
public class DebateController {

    @GetMapping("")
    public String list() {
        log.info("토론게시판................");
        return "/debate/list";
    }
    @GetMapping({"/read", "/modify"})
    public String read() {
        return "/debate/read";

    }

    @GetMapping("/write")
    public String write() {
        return "/debate/write";
    }
}
