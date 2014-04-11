package org.xsalefter.springjpaconverter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="application_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1019847930367728539L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="application_user_id")
	private Long id;

	@Column(name="name", nullable=false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable=false)
	@Convert(converter=GenderConverter.class)
	private Gender gender;

	@Column(name="address")
	private String address;

	protected User() {}

	public User(final String name, final Gender gender, final String address) {
		this.name = name;
		this.gender = gender;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
