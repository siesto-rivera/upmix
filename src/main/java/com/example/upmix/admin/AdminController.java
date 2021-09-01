package com.example.upmix.admin;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
@Log4j2
public class AdminController {

    @GetMapping("/Group")
    public String group_list() {

        return "/admin/group/list";
    }

    @GetMapping("/Group/read")
    public String group_read() {
        return "/admin/group/read";
    }

    @GetMapping("/Group/write")
    public String group_add() {
        return "/admin/group/write";
    }

    @GetMapping("/User")
    public String user_list() {

        return "/admin/user/list";
    }

    @GetMapping("/MyGroup")
    public String mygroup(){
        return "/admin/group/mygroup";
    }
}
