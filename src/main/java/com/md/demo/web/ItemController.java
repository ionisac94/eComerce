package com.md.demo.web;

import com.md.demo.dto.CommentDTO;
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
import org.springframework.util.CollectionUtils;
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

	@GetMapping(value = "/item/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CommentDTO>> getCommentsFromItem(@PathVariable("id") Integer id) {
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

	@GetMapping(value = "/item/{id}/rating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<RatingDTO>> getRatingFromItem(@PathVariable("id") Integer id) {
		LOGGER.info("About getting Item by id: " + id);

		Item itemById = itemService.getItemById(id);

		if (itemById == null) {
			LOGGER.error("Item with id {} was not found: ", id);
			return ResponseEntity.noContent().build();
		}

		LOGGER.info("About getting all rating from item: " + itemById);

		List<Rating> allRatingsForASpecificItem = ratingService.getAllRatingsForASpecificItem(id);

		List<RatingDTO> ratingDTOS = RatingDTO.toRatingDTOList(allRatingsForASpecificItem);
		if (CollectionUtils.isEmpty(ratingDTOS)) {
			LOGGER.error("Empty Collection was found with {} entities", ratingDTOS.size());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(ratingDTOS);
	}

	@GetMapping(value = "/item/{itemId}/averagerating", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Double> getAverageRatingFromItem(@PathVariable("itemId") Integer itemId) {
		LOGGER.info("About getting Item by itemId: " + itemId);

		Item itemById = itemService.getItemById(itemId);

		if (itemById == null) {
			LOGGER.error("Item with itemId {} was not found: ", itemId);
			return ResponseEntity.noContent().build();
		}

		LOGGER.info("About getting all rating from itemId: " + itemById);
		Double averageRatingForASpecificItem = ratingService.getAverageRatingForASpecificItem(itemId);

		return ResponseEntity.status(HttpStatus.OK).body(averageRatingForASpecificItem);
	}

	@DeleteMapping("item/{id}")
	public ResponseEntity deleteItemById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete an Item by id: " + id);

		Item itemById = itemService.getItemById(id);

		if (itemById == null) {
			LOGGER.error("Item with id {} was not found: ", id);
			return ResponseEntity.noContent().build();
		}
		boolean itemDeleted = itemService.isItemDeleted(id);

		LOGGER.info("Item with {} id was deleted :", itemById.getId(), itemDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Item succesufuly was deleted");
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
											 @RequestParam("rating") Double rating) {

		Rating ratingToSave = ratingService.addRating(itemId, rating);
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
