package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.service.CommentService;
import com.md.demo.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	private CommentService commentService;

	private ItemService itemService;

	public ItemController(CommentService commentService, ItemService itemService) {
		this.commentService = requireNonNull(commentService, "commentService is mandatory");
		this.itemService = requireNonNull(itemService, "itemService is mandatory");
		;
	}

	@GetMapping(value = "/item/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CommentDTO>> getCommentsFromASpecificItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);

		Item itemById = itemService.getItemById(id);

		if (itemById == null) {
			LOGGER.error("Item with id {} was not found: ", id);
			return ResponseEntity.noContent().build();
		}

		LOGGER.info("About getting all comments from item: " + itemById);

		List<Comment> allComments = commentService.getAllComments(id);

		List<CommentDTO> commentDTOS = CommentDTO.toCommentDTOList(allComments);
		if (CollectionUtils.isEmpty(commentDTOS)) {
			LOGGER.error("Empty Collection was found with {} entities", commentDTOS.size());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(commentDTOS);
	}

	@DeleteMapping("item/{id}")
	public ResponseEntity deleteItemById(@PathVariable("id") Integer id) {

		Item itemById = itemService.getItemById(id);

		if (itemById == null) {
			LOGGER.error("Item with id {} was not found: ", id);
			return ResponseEntity.noContent().build();
		}
		LOGGER.info("About to delete an Item with {} id", itemById.getId());
		itemService.deleteComment(id);

		return ResponseEntity.status(HttpStatus.OK).body("Item succesufuly was deleted");
	}
}
