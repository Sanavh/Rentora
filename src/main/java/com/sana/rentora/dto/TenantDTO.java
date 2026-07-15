package com.sana.rentora.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TenantDTO {
	
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message="Invalid Email Format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Gender is required")
	private String gender;
	
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
	private String phone;
	
	
	public TenantDTO() {}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
}
