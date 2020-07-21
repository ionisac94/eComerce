package com.md.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {

	@Id
	@TableGenerator(name = "Comment_Gen",
			table = "ID_GEN",
			pkColumnName = "GEN_NAME",
			valueColumnName = "GEN_VALUE")
	@GeneratedValue(generator = "Comment_Gen")
	@NotNull
	private Integer id;

	@NotNull
	private String content;

	@Version
	@NotNull
	private Integer version;

	@NotNull
	private LocalDateTime datePosted;

	//TODO do not forget to remember about Foreign Key Options from DB if u want to delete/update a parent row
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "itemId")
	@NotNull
	private Item itemId;
}
