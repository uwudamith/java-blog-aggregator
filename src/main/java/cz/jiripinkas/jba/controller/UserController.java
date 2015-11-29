package cz.jiripinkas.jba.controller;

import java.security.Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);
			
	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;
	
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}
	
	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}

	@RequestMapping("/register")
	public String showRegister() {
		return "user-register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute("user")User user) {
		userService.save(user); 
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model,Principal principal ){
		String name = principal.getName();
		model.addAttribute("user",userService.findOneWithBlogs(name));
		return "user-detail";
	}
	

	@RequestMapping(value="/account",method=RequestMethod.POST)
	public String doAddBlog(@ModelAttribute("blog")Blog blog,Principal principle) {
		String name = principle.getName();
		blogService.save(blog,name);
		return "redirect:/account.html";
	}
		
}
