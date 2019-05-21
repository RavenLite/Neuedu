package com.neuedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.model.po.Emp;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @RequestMapping("/testajax")
    public String ajax()
    {
        //{"username":"feiyy","age":20}
        //[{},{}]
        return "{\"username\":\"feiyy\"}";
    }

    @RequestMapping("/emps")
    public List<Emp> getAllEmps()
    {
        List list = new ArrayList<>();

        Emp e1 = new Emp();
        e1.setEmpno(1);
        e1.setEname("KING");

        Emp e2 = new Emp();
        e2.setEmpno(2);
        e2.setEname("SMITH");

        list.add(e1);
        list.add(e2);

        //jackson works to convert list to json str
        return list;
    }

    @RequestMapping("/getmap")
    public Map<String,Emp> getMap()
    {
        Map<String,Emp> m = new HashMap<>();

        Emp e = new Emp();
        e.setEmpno(1);
        e.setEname("CLARK");
        e.setHiredatestr("2018-4-2");

        m.put("CLARK", e); //{"CLARK":{"empno":1,"ename":"CLARK","hiredatestr":"2018-4-2"}}

        return m;

    }
}
