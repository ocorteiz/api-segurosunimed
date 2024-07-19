package com.example.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@NotEmpty(message = "email should not empty star")
	@Email
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "gender should not empty star")
	private String gender;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Adress> adresses = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Adress> getAdresses() {
		return adresses;
	}

	public void addAdress(Adress adress) {
		adresses.add(adress);
	}

}
