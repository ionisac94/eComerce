package com.md.demo.web;

import com.md.demo.dto.RatingDTO;
import com.md.demo.exception.NoSuchRatingExistException;
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

import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
public class RatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

	private RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = Objects.requireNonNull(ratingService, "ratingService is mandatory");
	}

	@GetMapping("rating/{id}")
	public ResponseEntity<RatingDTO> getRatingById(@PathVariable("id") Integer id) {
		try {
			LOGGER.info("About to get rating with {} id", id);
			Rating ratingById = ratingService.findRatingById(id);
			RatingDTO ratingDTO = RatingDTO.toRatingDTO(ratingById);
			return ResponseEntity.status(HttpStatus.OK).body(ratingDTO);
		} catch (NoSuchRatingExistException e) {
			LOGGER.error("Rating with id {} was not found! {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("rating/{id}")
	public ResponseEntity deleteRatingById(@PathVariable("id") Integer id) {
		try {
			LOGGER.info("About to delete a rating with {} id", id);
			ratingService.deleteRating(id);
			return ResponseEntity.status(HttpStatus.OK).body("Rating deleted successfully");
		} catch (NoSuchRatingExistException e) {
			LOGGER.error("Rating with id {} was not found! {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such rating was found in DB");
		}
	}
}
