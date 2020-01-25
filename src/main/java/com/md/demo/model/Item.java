package com.md.demo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private String description;

//	@Formula("select avg(r.rating) from Rating r where r.item_id = id")
	private Double rating;

	public Item() { // Default constructor for JPA
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
