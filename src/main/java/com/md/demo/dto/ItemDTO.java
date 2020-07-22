package com.md.demo.dto;

import lombok.Data;

@Data
public class ItemDTO {

	private String title;

	private String description;

	private Double averageRating;

	private UserDTO user;
}
