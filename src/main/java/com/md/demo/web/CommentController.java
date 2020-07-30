package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
import com.md.demo.mapper.CommentMapper;
import com.md.demo.model.Comment;
import com.md.demo.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@Api(description = "Perform CRUD operations on Comment Entity")
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	private final CommentService commentService;

	@ApiOperation(value = "Get all Comments")
	@GetMapping("/all")
	public ResponseEntity<?> getAllComments() {
		LOGGER.info("About to get all Comments");
		List<Comment> allComments = commentService.getAllComments();
		LOGGER.info("Comments were found successfully!");
		List<CommentDTO> commentDTOS = CommentMapper.COMMENT_MAPPER.toDtos(allComments);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTOS);
	}

	@ApiOperation(value = "Get a specific Comment by id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getComment(@PathVariable("id") Integer id) {
		LOGGER.info("About to get a Comment from DB with id: {}", id);
		Comment commentById = commentService.findCommentById(id);
		LOGGER.info("Comment was found successfully!");
		CommentDTO commentDTO = CommentMapper.COMMENT_MAPPER.toDto(commentById);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}

	@ApiOperation(value = "Delete a specific Comment by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete a Comment with id: {}", id);
		boolean commentIsDeleted = commentService.isCommentDeleted(id);
		LOGGER.info("Comment with id {} was deleted successfully {}", id, commentIsDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Comment was deleted successfully");
	}

	@ApiOperation(value = "Create a new Comment")
	@PostMapping("/createcomment")
	public ResponseEntity<?> createNewComment(@RequestBody Comment newComment) {
		Comment newCommentCreated = commentService.createComment(newComment);
		LOGGER.info("Comment was created successfully");
		CommentDTO commentDTO = CommentMapper.COMMENT_MAPPER.toDto(newCommentCreated);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}

	@ApiOperation(value = "Update a specific Comment by id")
	@PutMapping("/updatecomment/{commentId}")
	public ResponseEntity<?> updateComment(@RequestParam String newContent, @PathVariable Integer commentId) {
		LOGGER.info("About to update content field for a Comment with id: {}", commentId);
		commentService.updateComment(newContent, commentId);
		LOGGER.info("Comment with id {} was updated successfully", commentId);

		return ResponseEntity.status(HttpStatus.OK).body("Comment was updated successfully");
	}
}
