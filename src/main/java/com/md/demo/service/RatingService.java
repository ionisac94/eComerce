package com.md.demo.service;

import com.md.demo.model.Rating;

import java.util.List;

public interface RatingService {

	List<Rating> getAllRatingsForASpecificItem(Integer itemId);

	Rating findRatingById(Integer id);

	boolean isRatingDeleted(Integer id);
}
