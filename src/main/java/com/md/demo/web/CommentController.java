package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
import com.md.demo.mapper.CommentMapper;
import com.md.demo.model.Comment;
import com.md.demo.service.CommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	private final CommentService commentService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getComment(@PathVariable("id") Integer id) {
		LOGGER.info("About to get a Comment from DB with id: {}", id);
		Comment commentById = commentService.findCommentById(id);
		LOGGER.info("Comment was found successfully!");
		CommentDTO commentDTO = CommentMapper.COMMENT_MAPPER.toDto(commentById);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete a Comment with id: {}", id);
		boolean commentIsDeleted = commentService.isCommentDeleted(id);
		LOGGER.info("Comment with id {} was deleted successfully {}", id, commentIsDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Comment was deleted successfully");
	}

	@PostMapping("/createcomment")
	public ResponseEntity<?> createNewComment(@RequestBody Comment newComment) {
		Comment newCommentCreated = commentService.createComment(newComment);
		LOGGER.info("Comment was created successfully");
		CommentDTO commentDTO = CommentMapper.COMMENT_MAPPER.toDto(newCommentCreated);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}

	@PutMapping("/updatecomment/{commentId}")
	public ResponseEntity<?> updateComment(@RequestParam String newContent, @PathVariable Integer commentId) {
		LOGGER.info("About to update content field for a Comment with id: {}", commentId);
		commentService.updateComment(newContent, commentId);
		LOGGER.info("Comment with id {} was updated successfully", commentId);

		return ResponseEntity.status(HttpStatus.OK).body("Comment was updated successfully");
	}
}
