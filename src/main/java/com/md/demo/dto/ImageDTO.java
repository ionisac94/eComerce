package com.md.demo.dto;

import com.md.demo.model.Image;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {

	private Integer id;

	private String name;

	private LocalDateTime datePosted;

	public Integer item;

	public ImageDTO(Image image) {
		this.id = image.getId();
		this.name = image.getName();
		this.datePosted = image.getDatePosted();
		this.item = image.getItem().getId();
	}
}
