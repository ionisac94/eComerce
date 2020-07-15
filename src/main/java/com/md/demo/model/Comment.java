package com.md.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	@Id
	@TableGenerator(name = "Comment_Gen",
			table = "ID_GEN",
			pkColumnName = "GEN_NAME",
			valueColumnName = "GEN_VALUE")
	@GeneratedValue(generator = "Comment_Gen")
	private Integer id;

	@NotNull
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne
//	@OnDelete(action = OnDeleteAction.CASCADE) delete only Comment row from BD and not Item
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "item_id")
	@NotNull
	private Item itemId;
}
