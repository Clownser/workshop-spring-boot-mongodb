package com.clownser.workshopmongo.resourcers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clownser.workshopmongo.domain.Post;
import com.clownser.workshopmongo.resourcers.util.URL;
import com.clownser.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;



	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> finById(@PathVariable String id) {
		Post obj = service.findbyId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> finByTitle(@RequestParam(value = "text", defaultValue  ="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value = "text", defaultValue  ="") String text,
			@RequestParam(value = "minDate", defaultValue  ="") String minDate,
			@RequestParam(value = "maxDate", defaultValue  ="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullsearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}

}
