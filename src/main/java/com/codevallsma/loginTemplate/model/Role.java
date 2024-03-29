package com.codevallsma.loginTemplate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	@ColumnTransformer(
			read = "AES_DECRYPT(UNHEX(name), 'ankon')",
			write = "HEX(AES_ENCRYPT(?, 'ankon'))"
	)
	private String name;

	@Column
	private String description;

	public Role() {
	}
	public Role(String name,String description) {
		this.name=name;
		this.description = description;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
