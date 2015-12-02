package cz.jiripinkas.jba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.UserService;

@Controller
@RequestMapping("/users")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id) {
		User user = userService.findOne(id);
		List<Blog> blogs = blogService.findByUser(user);
		if (blogs.size() > 0) {
			return "redirect:/users.html?available=true&ref=" + id;
		} else {
			userService.delete(user);
			return "redirect:/users.html";
		}
	}

	@RequestMapping("/removeWithBlogs/{id}")
	public String removeUserWithBlogs(@PathVariable int id) {
		User user = userService.findOne(id);
		userService.delete(user);
		return "redirect:/users.html";
	}
}
