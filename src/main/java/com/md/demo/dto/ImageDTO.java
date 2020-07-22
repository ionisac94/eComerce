package com.md.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {

	private Integer id;

	private String name;

	private LocalDateTime datePosted;

	private ItemDTO item;
}
