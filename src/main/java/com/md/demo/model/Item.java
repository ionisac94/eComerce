package com.md.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	@Id
	@TableGenerator(name = "Item_Gen",
			table = "ID_GEN",
			pkColumnName = "GEN_NAME",
			valueColumnName = "GEN_VALUE")
	@GeneratedValue(generator = "Item_Gen")
	private Integer id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	//	@Formula("select avg(r.rating) from Rating r where r.item_id = id")
	private Double rating;
}
