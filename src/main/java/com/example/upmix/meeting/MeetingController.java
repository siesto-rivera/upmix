package com.example.upmix.meeting;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Meeting")
@Log4j2
public class MeetingController {

    @GetMapping({"", "/list"})
    public String list() {
        log.info("action................");
        return "/meeting/list";
    }

    @GetMapping("/write")
    public String write(){
        return "/meeting/write";
    }

    @GetMapping("/read")
    public String read() {
        return "/meeting/read";

    }
}
