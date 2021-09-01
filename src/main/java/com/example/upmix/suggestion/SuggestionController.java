package com.example.upmix.suggestion;

import com.example.upmix.domain.dto.BoardDTO;
import com.example.upmix.domain.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Suggestion")
@Log4j2
public class SuggestionController {
    @GetMapping({"", "/list"})
    public String list() {
        log.info("list................");
        return "/suggestion/list";
    }

    @GetMapping({"/read", "/modify"})
    public String read() {
        return "/suggestion/read";

    }

    @GetMapping("/write")
    public String write() {
        return "/suggestion/write";
    }


}
