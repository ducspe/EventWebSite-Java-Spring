package com.eventsite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventsite.domain.Author;
import com.eventsite.domain.Post;
import com.eventsite.repository.AuthorRepository;
import com.eventsite.repository.PostRepository;

@Component
public class DataLoader {

	private PostRepository postRepository;
	private AuthorRepository authorRepository;
	
	@Autowired
	public DataLoader(PostRepository postRepository,AuthorRepository authorRepository){
		this.postRepository = postRepository;
		this.authorRepository = authorRepository;
	}
	
	@PostConstruct
	private void loadData(){
		
		postRepository.deleteAll();
		authorRepository.deleteAll();
		
		// create an author
		Author dc = new Author("Danu","Caus", "danucaus@startupGmbh.de");
		authorRepository.save(dc);
		
		// create more authors
		createAuthors();
		
		// create some posts
		createPosts( dc );
		
	}
	
	private void createAuthors() {
		Author nc = new Author("Nicu","Colesnic","niku@startupGmbh.de");
		authorRepository.save(nc);
		Author bc = new Author("Berk","Cemberci","berk@startupGmbh.de");
		authorRepository.save(bc);
		Author cz = new Author("Cristi","Zara","chris@startupGmbh.de");
		authorRepository.save(cz);
		Author cb = new Author("Carsten","Bullemer","carsten@startupGmbh.de");
		authorRepository.save(cb);
		
	}

	private void createPosts(Author author) {
		
		Date yesterday = null;
		try {
			yesterday = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a").parse("12/18/2015 12:00:00 PM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Post meeting = new Post("Project meeting");
		meeting.setLocation("Room 506");
		meeting.setTime(new Date("4/05/2016 11:25"));
		meeting.setSummary( getMeetingSummary() );
		meeting.setBody( getMeetingBody() );
		meeting.setPostedOn( yesterday );
		meeting.setKeywords( getMeetingKeywords() );
		meeting.setActive(false);
		meeting.setAuthor( author );
		postRepository.save(meeting);
		
		Post soccer = new Post("Soccer!");
		soccer.setLocation("StadtPark");
		soccer.setTime(new Date("3/04/2016 11:45"));
		soccer.setSummary( getSoccerSummary() );
		soccer.setBody( getSoccerBody() );
		soccer.setPostedOn( new Date() );
		soccer.setKeywords( getSoccerKeywords() );
		soccer.setActive(true);
		soccer.setAuthor( author );
		postRepository.save(soccer);
		
	}

	private List<String> getSoccerKeywords(){
		List<String> keywords = new ArrayList<String>();
		keywords.add("money");
		keywords.add("ball");
		return keywords;
	}

	private List<String> getMeetingKeywords(){
		List<String> keywords = new ArrayList<String>();
		keywords.add("milestone");
		keywords.add("extension");
		return keywords;
	}
	
	private String getSoccerSummary(){
		return "<p>Soccer at stadtpark</p>";
	}
	
	private String getSoccerBody(){
		String body = "<p>Will be 20 people. Bring your clothes and Berk please bring your ball. Also dont forget the 1 euro for the rent of net and gates</p>";
		return body;
	}
	
	private String getMeetingSummary(){
		return "<p>New milestone</p>";
	}
	
	private String getMeetingBody(){
		String body = "<p>Will have to discuss how to extend the program according to the new milestone requirements. Please prepare suggestions everybody.</p>";
		return body;
	}
	
}