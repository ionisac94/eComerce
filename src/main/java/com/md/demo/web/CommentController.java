package com.md.demo.web;

import com.md.demo.model.Comment;
import com.md.demo.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class CommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


	@Autowired
	private CommentService commentService;


	@DeleteMapping("comment/{id}")
	public ResponseEntity deleteCommentById(@PathVariable("id") Long id) {
		try {
			LOGGER.info("About to delete a comment with {} id", id);
			commentService.deleteComment(id);
			return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
		}
	}

	@GetMapping("comment/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
		try {
			LOGGER.info("About to get comment with {} id", id);
			Comment commentById = commentService.findCommentById(id);
			return ResponseEntity.status(HttpStatus.OK).body(commentById);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
