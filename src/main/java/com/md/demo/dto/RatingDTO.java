package com.md.demo.dto;

import lombok.Data;

@Data
public class RatingDTO {

	private Integer id;

	private Integer value;

	private ItemDTO item;
}
