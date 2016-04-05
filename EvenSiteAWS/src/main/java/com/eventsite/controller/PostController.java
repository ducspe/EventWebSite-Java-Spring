package com.eventsite.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventsite.domain.Post;
import com.eventsite.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("posts", postService.list());
		return "post/list";
	}
	
	@RequestMapping("/view/{location}")
	public String view(@PathVariable(value="location") String location, Model model){
		model.addAttribute("post", postService.getBySlug(location));
		return "post/view";
	}
	
	
	@RequestMapping(value="/view/{from}/{to}" , method=RequestMethod.GET)
	public Iterable<Post> viewBetweenDates( @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) @PathVariable("from") Date fromDate, @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)  @PathVariable("to") Date toDate){
		
		return postService.getByDateBetween(fromDate, toDate);
		
	}
	
	
	
	
	
	@RequestMapping("/byAuthor/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("posts", postService.listByAuthor(id));
		return "post/list";
	}
	
	
	
}
