package com.eventsite.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventsite.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	Post findFirstByOrderByPostedOnDesc();
	
	List<Post> findAllByOrderByPostedOnDesc();

	Post findByLocation(String location);

	List<Post> findAllByAuthorIdOrderByPostedOnDesc(Long id);

	List<Post> findAllByPostedOnBetween(Date startDate, Date stopDate);

}
