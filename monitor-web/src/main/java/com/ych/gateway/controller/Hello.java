package com.ych.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhao.ych
 * @date 2018-07-01
 */
@RestController
public class Hello {

    @GetMapping("/hhh")
    public String he() {
        return "hehe";
    }

}
