package com.md.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	@TableGenerator(name = "User_Gen",
			table = "ID_GEN",
			pkColumnName = "GEN_NAME",
			valueColumnName = "GEN_VALUE")
	@GeneratedValue(generator = "User_Gen")
	private Integer id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	@Column(unique = true)
	private String password;

	@NotNull
	private LocalDateTime dateCreated;
}
