package fr.m2i.demo.front.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import fr.m2i.demo.front.model.MUser;
import fr.m2i.demo.front.model.Users;
import fr.m2i.demo.front.repositories.UserProxy;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/front/user")
public class UserController {

	private UserProxy userProxy;

	public UserController(UserProxy userProxy) {
		this.userProxy = userProxy;
	}

	@GetMapping
	public ModelAndView getUserForm() {
		MUser user = new MUser();
		ModelAndView mav = new ModelAndView("userForm");
		mav.addObject("user", user);
		return mav;
	}

	@GetMapping("/{username}")
	public String getUserByUsername(@PathVariable(name = "username") String username, Model model) {
		try {
			MUser user = this.userProxy.getUserByUsername(username);
			model.addAttribute("user", user);
		} catch (HttpClientErrorException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return "userDetail";
	}

	@PostMapping
	public ModelAndView createUser(@Valid @ModelAttribute(name = "user") MUser user, BindingResult result) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("userForm");
			mav.addObject("user", user);
			return mav;
		} else {
			this.userProxy.save(user);
			ModelAndView mav = new ModelAndView("redirect:/front/user/" + user.getUsername());
			return mav;
		}
	}
	
	@GetMapping("/login")
	public ModelAndView getLoginForm(Model model) {
		MUser loginUser = new MUser();
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginUser", loginUser);
		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute(name = "user") MUser loginUser, HttpSession session) {
		try {
			String result = this.userProxy.login(loginUser);
			session.setAttribute("authToken", result);
			ModelAndView mav = new ModelAndView("redirect:/front");
			return mav;
		} catch (HttpClientErrorException ex) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("loginUser", loginUser);
			return mav;
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/front/user/login";
	}

}