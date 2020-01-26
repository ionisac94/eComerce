package com.md.demo.dto;

import com.md.demo.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingDTO {

	private Long id;

	private Long itemId;


	public RatingDTO(Rating rating) {
		this.id = rating.getId();
		this.itemId = rating.getItem().getId();
	}


	public static List<RatingDTO> toRatingDTOList(List<Rating> ratingList) {

		List<RatingDTO> list = new ArrayList<>();
		for (Rating rating : ratingList) {
			RatingDTO ratingDTO = new RatingDTO(rating);
			list.add(ratingDTO);
		}
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
