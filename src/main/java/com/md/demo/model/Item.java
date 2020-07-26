package com.md.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

	@Id
	@TableGenerator(name = "Item_Gen",
			table = "ID_GEN",
			pkColumnName = "GEN_NAME",
			valueColumnName = "GEN_VALUE")
	@GeneratedValue(generator = "Item_Gen")
	@NotNull
	private Integer id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	@Column(name = "averageRating")
	private Double averageRating;

	@NotNull
	private LocalDateTime datePosted;

	//TODO do not forget to remember about Foreign Key Options from DB if u want to delete/update a parent row
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//	An alternative option from Hibernate to delete a raw that has a foreign-key!!!
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userId")
	@NotNull
	private User user;
}
