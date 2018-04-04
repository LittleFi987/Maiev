package com.ych.gateway.controller;

import com.ych.core.common.Response;
import com.ych.gateway.annotations.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhao.ye on 01/04/2018.
 */
@RestController
public class DashboardController {


    @GetMapping("/dashboard")
    public Response dashboard(@CurrentUser Integer userId) {

    }


}
