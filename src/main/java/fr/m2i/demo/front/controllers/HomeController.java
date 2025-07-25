package fr.m2i.demo.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.m2i.demo.front.repositories.UserProxy;

@Controller
@RequestMapping("/front")
public class HomeController {
	
	private UserProxy userProxy;
	
	public HomeController(UserProxy userProxy) {
		this.userProxy = userProxy;
	}

	@GetMapping
	public String getHomePage(Model model) {
		model.addAttribute("users", userProxy.getAllUsers());
		return "index";
	}
	
}