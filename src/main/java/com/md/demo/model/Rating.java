package com.md.demo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Double rating;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	public User user;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@NotNull
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Item item;

	public Rating() {
	}

	public Rating(Double rating, User user, Item item) {
		this.rating = rating;
		this.user = user;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
