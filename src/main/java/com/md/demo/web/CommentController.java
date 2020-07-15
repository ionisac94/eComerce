package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
import com.md.demo.model.Comment;
import com.md.demo.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

@RestController
public class CommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = requireNonNull(commentService, "commentService is mandatory");
	}

	@GetMapping("comment/{id}")
	public ResponseEntity<?> getCommentById(@PathVariable("id") Integer id) {
		LOGGER.info("About to get a comment from DB with {} id", id);
		Comment commentById = commentService.findCommentById(id);
		LOGGER.info("Comment was found successfully");
		CommentDTO commentDTO = CommentDTO.toCommentDTO(commentById);
		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);

	}

	@DeleteMapping("comment/{id}")
	public ResponseEntity<String> deleteCommentById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete a comment with {} id", id);
		boolean commentIsDeleted = commentService.isCommentDeleted(id);
		LOGGER.info("Comment with {} id was deleted: ", id, commentIsDeleted);
		return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
	}
}
