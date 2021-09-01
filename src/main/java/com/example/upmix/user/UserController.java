package com.example.upmix.user;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MyPage")
@Log4j2
public class UserController {
    @GetMapping("")
    public String list() {

        return "/user/mypage";
    }
}
