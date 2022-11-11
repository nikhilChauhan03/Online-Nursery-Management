package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@Column(unique = true)
	@NotNull(message = "userName should not be null")
	@NotBlank(message = "userName should not be blank")
	@Size(min = 3, message = "length of userName should be minimum 3 ")
	private String userName;
	
	@NotNull(message = "password should not be null")
	@NotBlank(message = "password should not be blank")
	@Size(min = 6, message = "length of password should be minimum 6")
	private String password;
	
	@Size(min=3, message = "Minimum adminName length shoudl be 3")
	@NotNull(message = "adminName should not be Null")
	@NotBlank(message = "adminName should not blank")
	private String adminName;
	
	@Column(unique = true)
	@Email
	@NotNull(message = "adminEmail should not be Null")
	@NotBlank(message = "adminEmail should not blank")
	private String adminEmail;
	
	@NotNull(message = "address should not be null")
	@Embedded
	private Address address;
	
}
