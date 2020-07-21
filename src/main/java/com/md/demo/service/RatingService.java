package com.md.demo.service;

import com.md.demo.model.Item;
import com.md.demo.model.Rating;

import java.util.List;

public interface RatingService {

	Rating findRatingById(Integer id);

	boolean isRatingDeleted(Integer id);

	List<Rating> getAllRatingsForASpecificItem(Integer itemId);

	Double getAverageRatingForASpecificItem(Integer itemId);

	Double getAverageRating(Item itemId);

	Rating addRating(Integer itemId, Integer value);

	Rating createRating(Rating rating);

	void updateRating(Integer newValue, Integer ratingId);
}
