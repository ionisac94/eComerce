package com.md.demo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY) // does the same as @OnDelete(action = OnDeleteAction.CASCADE)
//	@ManyToOne
//	@OnDelete(action = OnDeleteAction.CASCADE) delete only Comment row from BD and not Item
	@JoinColumn(name = "item_id")
	@NotNull
	public Item item;

	@NotNull
	private String comment;

	public Comment() { // Default constructor for JPA
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
