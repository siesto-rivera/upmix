package com.example.upmix.suggestion;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Suggestion")
@Log4j2
public class SuggestionController {
    @GetMapping({"/", "/list"})
    public String list() {
        log.info("list................");
        return "/suggestion/list";
    }
}
