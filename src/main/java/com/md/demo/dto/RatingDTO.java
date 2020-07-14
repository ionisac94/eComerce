package com.md.demo.dto;

import com.md.demo.model.Rating;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RatingDTO {

	private Integer id;

	private Integer itemId;


	public RatingDTO(Rating rating) {
		this.id = rating.getId();
		this.itemId = rating.getItemId().getId();
	}


	public static List<RatingDTO> toRatingDTOList(List<Rating> ratingList) {
		return ratingList.stream().map(RatingDTO::new).collect(Collectors.toList());
	}
}
