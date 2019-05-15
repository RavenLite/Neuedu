package com.neuedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    /*
     *
     *pay attention: use Integer(if age is not present, age is set to null),
     *not int(if age is not present, error will happen)
     */
    @RequestMapping("/login")
    public String test(String username,String password)
    {
        //invoke service method to query database.

        System.out.println(username);
        System.out.println(password);

        return "index.jsp";
    }

}
