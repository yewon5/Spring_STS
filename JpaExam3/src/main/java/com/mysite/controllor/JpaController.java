package com.mysite.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jpa")
public class JpaController {

    @GetMapping("/memberWriteForm")
    public String memberWriteForm() {
        return "jpa/memberRegister";
    }
}
