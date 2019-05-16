package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.model.po.Emp;
import com.neuedu.model.po.User;

@Controller
@RequestMapping("/test")
public class TestController {
	
	/*po(posistent object)��vo(value object)����*/
	
	/*
	 * 
	 *pay attention: use Integer(if age is not present, age is set to null), 
	 *not int(if age is not present, error will happen)
	 */
	@PostMapping("/login")
	public String test(String username,String password, HttpSession session)
	{		
		//invoke service method to query database.
		//get userinfo(userid, username...... ), put it into session
		
		//getsession by request.getSession();
		
		System.out.println(username);
		System.out.println(password);
		
		//put info in session
		session.setAttribute("userinfo", username);
				
		return "/index.jsp";
	}
	
	@PostMapping("/register")
	public String register(User u, MultipartFile photo)
	{
		System.out.println(u.getUsername());
		System.out.println(u.getPassword());
		
		//the photo we have this is location somewhere in your temparary folder.
		//put it in D://
		File destination = new File("/Users/raven/Downloads/test",photo.getOriginalFilename());
		try {
			photo.transferTo(destination); //after transfer to the destination, the temparary file will be deleted automatically.
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/index.jsp";
	}
	
	@GetMapping("/emps")
	public String queryAllEmps(HttpServletRequest request)
	{
		//supposed to invoke service method get data from database
		List<Emp> list = new ArrayList<>();
		
		Emp e = new Emp();
		e.setEmpno(1);
		e.setEname("james");
		e.setJob("teacher");
		e.setHiredatestr("2019-5-16");
		
		list.add(e);
		
		//put the list into request
		request.setAttribute("list", list);
		request.setAttribute("msg", "helloworld");
		
		/**
		 * difference between forward and redirect 
		 * 
		 * forward: controller and jsp share the same request.
		 * redirect: controller and jsp have different request.
		 */
		
		//forward to this page(share the same request)
		//redirect to this page
		return "/emps.jsp"; 
		
	}
	
	/**
	 * 
	 * restful request
	 */
	@GetMapping("/rest/{username}/{password}")
	public String test2(@PathVariable String username, @PathVariable String password)
	{
		System.out.println(username);
		System.out.println(password);
		
		return "/index.jsp";
		
	}

}
