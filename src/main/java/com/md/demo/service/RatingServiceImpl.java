package com.md.demo.service;

import com.md.demo.exception.NoSuchRatingExistException;
import com.md.demo.exception.RatingValidationValueException;
import com.md.demo.model.Item;
import com.md.demo.model.Rating;
import com.md.demo.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

	private final RatingRepository ratingRepository;

	private final ItemService itemService;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<Rating> getAllRatingsForASpecificItem(Integer itemId) {
		LOGGER.info("About getting all comments from DB");

		List<Rating> ratingsByItemId = ratingRepository.findAllRatingByItemId(itemId);

		if (ratingsByItemId == null) {
			return Collections.emptyList();
		} else {
			return ratingsByItemId;
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Double getAverageRatingForASpecificItem(Integer itemId) {
		LOGGER.info("About getting rating from DB");

		return ratingRepository.getAverageRatingByItemId(itemId);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<Rating> findAllRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Rating findRatingById(Integer id) {
		Optional<Rating> optionalRating = ratingRepository.findById(id);
		LOGGER.info("Fetched a Rating from DB: {}", optionalRating);

		return optionalRating.orElseThrow(() -> new NoSuchRatingExistException("No such Rating in DB!"));
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public boolean isRatingDeleted(Integer id) {
		boolean ratingIsDeleted;
		LOGGER.warn("Checking if Rating with id: {} exists in DB", id);
		Rating ratingById = findRatingById(id);
		LOGGER.info("Rating with id {} exists in DB", id);
		if (ratingById.getId() != null) {
			ratingRepository.deleteById(id);
			return ratingIsDeleted = true;
		} else {
			throw new NoSuchRatingExistException("No such Rating in DB!");
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Rating addRating(Integer itemId, Integer value) {
		Item itemById = itemService.getItemById(itemId);

		if (value <= 5 && value > 0) {
			Rating newRating = Rating.builder().value(value).item(itemById).build();

			return ratingRepository.save(newRating);
		} else {
			throw new RatingValidationValueException("Rating`s value should match between [0:5) units :)");
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Double getAverageRating(Item itemId) {
		return ratingRepository.getAverageRating(itemId);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Rating createRating(Rating rating) {
		LOGGER.info("Creating a new Rating: {}", rating);
		return ratingRepository.save(rating);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateRating(Integer newValue, Integer ratingId) {
		findRatingById(ratingId);
		ratingRepository.updateRatingValue(newValue, ratingId);
	}
}
