package com.eventsite.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventsite.domain.Post;
import com.eventsite.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository){
		this.postRepository = postRepository;
	}
	
	public Post getLatestPost(){
		return postRepository.findFirstByOrderByPostedOnDesc();
	}

	public List<Post> list() {
		return postRepository.findAllByOrderByPostedOnDesc();
	}

	public Post getBySlug(String location) {
		return postRepository.findByLocation(location);
	}

	public List<Post> listByAuthor(Long id) {
		return postRepository.findAllByAuthorIdOrderByPostedOnDesc(id);
	}

	public Post get(Long id) {
		return postRepository.findOne(id);
	}

	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	public void delete(Long id) {
		postRepository.delete(id);
	}

	public List<Post> getByDateBetween(Date startDate, Date stopDate) {
		return postRepository.findAllByPostedOnBetween(startDate, stopDate);
	}
}
