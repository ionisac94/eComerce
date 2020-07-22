package com.md.demo.web;

import com.md.demo.dto.RatingDTO;
import com.md.demo.mapper.RatingMapper;
import com.md.demo.model.Rating;
import com.md.demo.service.RatingService;
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
@RequestMapping("/rating")
public class RatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

	private final RatingService ratingService;

	@GetMapping("/getrating/{id}")
	public ResponseEntity<?> getRatingById(@PathVariable("id") Integer id) {
		LOGGER.info("About to get a Rating with id: {}", id);
		Rating ratingById = ratingService.findRatingById(id);
		LOGGER.info("Rating with id {} was found successfully!", id);
		RatingDTO ratingDTO = RatingMapper.RATING_MAPPER.toDto(ratingById);

		return ResponseEntity.status(HttpStatus.OK).body(ratingDTO);
	}

	@DeleteMapping("/deleterating/{id}")
	public ResponseEntity<?> deleteRatingById(@PathVariable("id") Integer id) {
		LOGGER.info("About to delete a Rating with id: {}", id);
		boolean ratingDeleted = ratingService.isRatingDeleted(id);
		LOGGER.info("Rating with id {} was deleted successfully {} ", id, ratingDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Rating was deleted successfully!");
	}

	@PostMapping("/createrating")
	public ResponseEntity<?> createRating(@RequestBody Rating rating) {
		LOGGER.info("About to create a new Rating: {}", rating);
		Rating newRating = ratingService.createRating(rating);
		LOGGER.info("Rating was created successfully {}", rating);

		return ResponseEntity.status(HttpStatus.OK).body("ratingDTO");
	}

	@PutMapping("/updaterating/{id}")
	public ResponseEntity<?> updateRating(@RequestParam Integer newValue, @PathVariable Integer id) {
		LOGGER.info("About to update value field for a Rating with id: {}", id);
		ratingService.updateRating(newValue, id);
		LOGGER.info("Rating with id {} was updated successfully!", id);

		return ResponseEntity.status(HttpStatus.OK).body("Rating was updated successfully");
	}
}
