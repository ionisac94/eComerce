package com.md.demo.service;

import com.md.demo.exception.NoSuchRatingExistException;
import com.md.demo.model.Rating;
import com.md.demo.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

	private RatingRepository ratingRepository;

	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = Objects.requireNonNull(ratingRepository, "ratingRepository is mandatory");
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Rating> getAllRatingsForASpecificItem(Integer itemId) {
		LOGGER.info("About getting comments from DB");

		List<Rating> ratingsByItemId = ratingRepository.findAllRatingByItemId(itemId);

		if (ratingsByItemId == null) {
			return Collections.emptyList();
		} else {
			return ratingsByItemId;
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Rating findRatingById(Integer id) {
		Optional<Rating> optionalRating = ratingRepository.findById(id);
		return optionalRating.orElseThrow(() -> new NoSuchRatingExistException("No such rating in DB"));
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteRating(Integer id) {
		Rating ratingById = findRatingById(id);
		if (ratingById.getId() != null) {
			ratingRepository.deleteById(id);
		} else {
			throw new NoSuchRatingExistException("No such rating in DB");
		}
	}
}
