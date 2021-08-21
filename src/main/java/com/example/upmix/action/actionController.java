package com.example.upmix.action;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Action")
@Log4j2
public class actionController {

    @GetMapping({"/", "/list"})
    public String list() {
        log.info("action................");
        return "/action/list";
    }

}
