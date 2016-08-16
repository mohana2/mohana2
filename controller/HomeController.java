package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.Login;
import com.model.Register;
import com.service.LoginService;
import com.service.RegisterService;

@Controller
public class HomeController {
	@Autowired(required = true)
	LoginService ls;
	@Autowired(required = true)
	RegisterService rs;


	@RequestMapping("/")
	public String getHome() {
		return "index";

	}

	@RequestMapping(value = "/login")
	public String getlogin() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginuser(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
		boolean isvalidUser = false;
		Login lo = new Login();
		lo.setName(name);
		lo.setPassword(pwd);
		ls.disc(lo);
		isvalidUser = ls.check(lo);
		ModelAndView mv = new ModelAndView("home");
		if (isvalidUser == true) {
			mv.addObject("msg", "hello welcome");
			mv.addObject("name", lo.getName());
		}
		return mv;
	}

	@RequestMapping(value = "/fSignup")
	public String getregister() {
		return "Registration";
	}

	@ModelAttribute("Registration")
	public Register gt() {
		return new Register();
	}

	@RequestMapping(value = "/Signup", method = RequestMethod.POST)
	public ModelAndView reguser(@ModelAttribute("Registration") Register register) {
		System.out.println(register);
		ModelAndView mc = new ModelAndView("Login");
		rs.r(register);
		return mc;
	}
}
	

