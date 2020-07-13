package com.md.demo.dto;

import com.md.demo.model.Rating;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RatingDTO {

	private Integer id;

	private Integer itemId;


	public RatingDTO(Rating rating) {
		this.id = rating.getId();
		this.itemId = rating.getItemId().getId();
	}


	public static List<RatingDTO> toRatingDTOList(List<Rating> ratingList) {

		List<RatingDTO> list = new ArrayList<>();
		for (Rating rating : ratingList) {
			RatingDTO ratingDTO = new RatingDTO(rating);
			list.add(ratingDTO);
		}
		return list;
	}
}
