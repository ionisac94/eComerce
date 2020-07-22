package com.md.demo.dto;

import lombok.Data;

@Data
public class CommentDTO {

	private Integer id;

	private ItemDTO item;

	private String content;
}
