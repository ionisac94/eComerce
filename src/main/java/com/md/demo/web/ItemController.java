package com.md.demo.web;

import com.md.demo.dto.ItemDTO;
import com.md.demo.mapper.ItemMapper;
import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.model.Rating;
import com.md.demo.service.CommentService;
import com.md.demo.service.ItemService;
import com.md.demo.service.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "Perform CRUD operations on Item Entity")
@AllArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	private final CommentService commentService;

	private final ItemService itemService;

	private final RatingService ratingService;

	@ApiOperation(value = "Get all Items")
	@GetMapping("/all")
	public ResponseEntity<?> getAllItems() {
		LOGGER.info("About to get all Items");
		List<Item> allItems = itemService.getAllItems();
		LOGGER.info("Items were found successfully!");
		List<ItemDTO> itemDTOS = ItemMapper.ITEM_MAPPER.toDtos(allItems);

		return ResponseEntity.status(HttpStatus.OK).body(itemDTOS);
	}

	@ApiOperation(value = "Get a specific item by id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getItemById(@PathVariable("id") Integer id) {
		LOGGER.info("About to get an Item by id: " + id);
		Item itemById = itemService.getItemById(id);
		Double averageRating = ratingService.getAverageRating(itemById);
		itemById.setAverageRating(averageRating);

		return ResponseEntity.status(HttpStatus.OK).body("itemDTO");
	}

	@ApiOperation(value = "Delete a specific item by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItemById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete an Item by id: " + id);
		boolean itemDeleted = itemService.isItemDeleted(id);
		LOGGER.warn("Item with id {} was deleted: {}", id, itemDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Item was deleted successfully");
	}

	@ApiOperation(value = "Get all comments for a specific item")
	@GetMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getCommentsFromItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);
		Item itemById = itemService.getItemById(id);

		LOGGER.info("About getting all comments from item: " + itemById);
		commentService.getAllCommentsByItemId(id);

		return ResponseEntity.status(HttpStatus.OK).body("commentDTOS");
	}

	@ApiOperation(value = "Get rating for a specific item")
	@GetMapping(value = "/{id}/rating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getRatingFromItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);
		Item itemById = itemService.getItemById(id);
		LOGGER.info("About getting all rating from item: " + itemById);
		List<Rating> allRatingsForASpecificItem = ratingService.getAllRatingsForASpecificItem(id);

		return ResponseEntity.status(HttpStatus.OK).body("ratingDTOS");
	}

	@GetMapping(value = "/{itemId}/averagerating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAverageRatingFromItem(@PathVariable("itemId") Integer itemId) {
		LOGGER.info("About getting Item by itemId: " + itemId);
		Item itemById = itemService.getItemById(itemId);
		LOGGER.info("About getting all rating from itemId: " + itemById);
		Double averageRatingForASpecificItem = ratingService.getAverageRatingForASpecificItem(itemId);

		return ResponseEntity.status(HttpStatus.OK).body(averageRatingForASpecificItem);
	}

	@ApiOperation(value = "Create a new Comment for a specific Item")
	@PostMapping("/{itemId}/addComment")
	public ResponseEntity<?> addCommentToItem(@PathVariable("itemId") Integer itemId, @RequestParam("comment") String content) {
		commentService.addComment(itemId, content);

		return ResponseEntity.status(HttpStatus.OK).body("commentDTO");
	}

	@ApiOperation(value = "Add a new rating for a specific Item")
	@PostMapping("/{itemId}/addRating")
	public ResponseEntity<?> addRatingToItem(@PathVariable("itemId") Integer itemId,
											 @RequestParam("newRating") Integer newRating) {

		ratingService.addRating(itemId, newRating);

		return ResponseEntity.status(HttpStatus.OK).body("ratingDTO");
	}

	@ApiOperation(value = "Update a specific comment for a specific item")
	@PutMapping("/{itemId}/modifyComment/{commentId}")
	public ResponseEntity<?> modifyCommentToItem(@PathVariable("itemId") Integer itemId,
												 @PathVariable("commentId") Integer commentId,
												 @RequestParam("comment") String commentToBeModified) {
		Comment commentToSave = commentService.modifyComment(itemId, commentId, commentToBeModified);

		return ResponseEntity.status(HttpStatus.OK).body("commentDTO");
	}
}


