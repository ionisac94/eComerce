package com.md.demo.web;

import com.md.demo.dto.RatingDTO;
import com.md.demo.model.Rating;
import com.md.demo.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

	private RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = Objects.requireNonNull(ratingService, "ratingService is mandatory");
	}

	@GetMapping("rating/{id}")
	public ResponseEntity<?> getRatingById(@PathVariable("id") Integer id) {
		LOGGER.info("About to get rating with {} id", id);
		Rating ratingById = ratingService.findRatingById(id);
		LOGGER.info("Rating was found successfully");
		RatingDTO ratingDTO = RatingDTO.toRatingDTO(ratingById);
		return ResponseEntity.status(HttpStatus.OK).body(ratingDTO);
	}

	@DeleteMapping("rating/{id}")
	public ResponseEntity<?> deleteRatingById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete a rating with {} id", id);
		boolean ratingDeleted = ratingService.isRatingDeleted(id);
		LOGGER.info("Rating with {} id was deleted: ", id, ratingDeleted);
		return ResponseEntity.status(HttpStatus.OK).body("Rating was deleted successfully");
	}
}
