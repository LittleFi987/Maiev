package com.ych;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenhao.ye on 11/03/2018.
 */
@Controller
@RequestMapping("/api")
public class Hello {

    public Hello() {
    }

    @RequestMapping("/hello")
    public void sayHello(int name) {
        System.out.println(name + ": hello");
    }


}
