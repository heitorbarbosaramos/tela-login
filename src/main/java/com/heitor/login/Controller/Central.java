package com.heitor.login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/")
public class Central {

    @GetMapping
    public String home(){
        return "central";
    }
}
