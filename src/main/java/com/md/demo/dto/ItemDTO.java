package com.md.demo.dto;

import com.md.demo.model.Item;
import lombok.Data;

@Data
public class ItemDTO {

	private String title;

	private String description;

	private Double averageRating;

	public ItemDTO(Item item) {
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.averageRating = item.getAverageRating();
	}

	public static ItemDTO toItemDTO(Item item) {
		return new ItemDTO(item);
	}
}
