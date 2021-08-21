package com.example.upmix.vote;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Vote")
@Log4j2
public class voteController {

    @GetMapping("/")
    public String vote() {
        log.info("vote................");
        return "/vote/vote";
    }
}
