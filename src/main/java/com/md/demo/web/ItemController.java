package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
import com.md.demo.dto.ItemDTO;
import com.md.demo.dto.RatingDTO;
import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.model.Rating;
import com.md.demo.service.CommentService;
import com.md.demo.service.ItemService;
import com.md.demo.service.RatingService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
public class ItemController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	private CommentService commentService;

	private ItemService itemService;

	private RatingService ratingService;

	public ItemController(CommentService commentService, ItemService itemService, RatingService ratingService) {
		this.commentService = requireNonNull(commentService, "commentService is mandatory");
		this.itemService = requireNonNull(itemService, "itemService is mandatory");
		this.ratingService = requireNonNull(ratingService, "ratingService is mandatory");
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<?> getItemById(@PathVariable("id") Integer id) {
		LOGGER.info("About to get an Item by id: " + id);
		Item itemById = itemService.getItemById(id);
		Double averageRating = ratingService.getAverageRating(itemById);
		itemById.setAverageRating(averageRating);
		ItemDTO itemDTO = ItemDTO.toItemDTO(itemById);

		return ResponseEntity.status(HttpStatus.OK).body(itemDTO);
	}

	@DeleteMapping("/item/{id}")
	public ResponseEntity deleteItemById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete an Item by id: " + id);
		boolean itemDeleted = itemService.isItemDeleted(id);
		LOGGER.warn("Item with id {} was deleted: {}", id, itemDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Item successfully was deleted");
	}

	@GetMapping(value = "/item/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CommentDTO>> getCommentsFromItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);
		Item itemById = itemService.getItemById(id);

		LOGGER.info("About getting all comments from item: " + itemById);
		List<Comment> allComments = commentService.getAllComments(id);
		List<CommentDTO> commentDTOS = CommentDTO.toCommentDTOList(allComments);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTOS);
	}

	@GetMapping(value = "/item/{id}/rating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<RatingDTO>> getRatingFromItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);
		Item itemById = itemService.getItemById(id);
		LOGGER.info("About getting all rating from item: " + itemById);
		List<Rating> allRatingsForASpecificItem = ratingService.getAllRatingsForASpecificItem(id);
		List<RatingDTO> ratingDTOS = RatingDTO.toRatingDTOList(allRatingsForASpecificItem);

		return ResponseEntity.status(HttpStatus.OK).body(ratingDTOS);
	}

	@GetMapping(value = "/item/{itemId}/averagerating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Double> getAverageRatingFromItem(@PathVariable("itemId") Integer itemId) {
		LOGGER.info("About getting Item by itemId: " + itemId);
		Item itemById = itemService.getItemById(itemId);
		LOGGER.info("About getting all rating from itemId: " + itemById);
		Double averageRatingForASpecificItem = ratingService.getAverageRatingForASpecificItem(itemId);

		return ResponseEntity.status(HttpStatus.OK).body(averageRatingForASpecificItem);
	}

	@PostMapping("/items/{itemId}/addComment")
	public ResponseEntity<?> addCommentToItem(@PathVariable("itemId") Integer itemId,
											  @RequestParam("comment") String content) {

		Comment commentToSave = commentService.addComment(itemId, content);
		CommentDTO commentDTO = new CommentDTO(commentToSave);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}

	@PostMapping("/items/{itemId}/addRating")
	public ResponseEntity<?> addRatingToItem(@PathVariable("itemId") Integer itemId,
											 @RequestParam("newRating") Integer newRating) {

		Rating ratingToSave = ratingService.addRating(itemId, newRating);
		RatingDTO ratingDTO = new RatingDTO(ratingToSave);

		return ResponseEntity.status(HttpStatus.OK).body(ratingDTO);
	}

	@PutMapping("/items/{itemId}/modifyComment/{commentId}")
	public ResponseEntity<?> modifyCommentToItem(@PathVariable("itemId") Integer itemId,
												 @PathVariable("commentId") Integer commentId,
												 @RequestParam("comment") String commentToBeModified) {
		Comment commentToSave = commentService.modifyComment(itemId, commentId, commentToBeModified);
		CommentDTO commentDTO = new CommentDTO(commentToSave);

		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
	}
}
