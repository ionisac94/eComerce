package com.md.demo.web;

import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.service.CommentService;
import com.md.demo.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);


	@Autowired
	private CommentService commentService;

	@Autowired
	private ItemService itemService;

	@GetMapping(value = "/item/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Comment>> getCommentsFromASpecificItem(@PathVariable("id") Long id) {
		LOGGER.info("About getting Item by id: " + id);

		Item itemById = itemService.getItemById(id);

		if (itemById == null) {
			LOGGER.error("Item with id {} was not found: ", id);
			return ResponseEntity.noContent().build();
		}

		LOGGER.info("About getting allComments by itemById: " + itemById);

		List<Comment> allComments = commentService.getAllComments(itemById);

		if (CollectionUtils.isEmpty(allComments)) {
			LOGGER.error("Empty Collection was found  `{}`", allComments.size());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(allComments);
	}

	@DeleteMapping("item/{id}")
	public ResponseEntity deleteItemById(@PathVariable("id") Long id) {

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
