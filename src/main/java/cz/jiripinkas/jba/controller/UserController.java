package cz.jiripinkas.jba.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.ItemService;
import cz.jiripinkas.jba.service.UserService;

@Controller
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private ItemService ItemService;

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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,BindingResult result) {
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "user-detail";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(Model model,@Valid @ModelAttribute("blog") Blog blog, BindingResult result,Principal principal) {
		if(result.hasErrors()){
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}

	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog = blogService.findOne(id);
		int count = ItemService.findByBlog(blog);
		if (count > 0) {
			return "redirect:/account.html?available=true&ref=" + id;
		} else {
			blogService.delete(blog);
			return "redirect:/account.html";
		}
	}

	@RequestMapping("/blog/removeWithItems/{id}")
	public String removeBlogWithItems(@PathVariable int id) {
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}

	@RequestMapping("/users/remove/{id}")
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

	@RequestMapping("/users/removeWithBlogs/{id}")
	public String removeUserWithBlogs(@PathVariable int id) {
		User user = userService.findOne(id);
		userService.delete(user);
		return "redirect:/users.html";
	}
}
