package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@Size(min=3, message = "Minimum customerName length shoudl be 3")
	@NotNull(message = "customerName should not be Null")
	@NotBlank(message = "customerName should not blank")
	private String customerName;
	
	@Column(unique = true)
	@Email
	@NotNull(message = "customerEmail should not be Null")
	@NotBlank(message = "customerEmail should not blank")
	private String customerEmail;
	
	@Column(unique = true)
	@Size(min=3, message = "Minimum userName length shoudl be 6")
	@NotNull(message = "userName should not be Null")
	@NotBlank(message = "userName should not blank")
	private String userName;
	
	@Size(min=3, message = "Minimum userPassword length shoudl be 6")
	@NotNull(message = "userPassword should not be Null")
	@NotBlank(message = "userPassword should not blank")
	private String userPassword;

	@NotNull(message = "address should not be null")
	@Embedded
	private Address userAddress;
	
	@OneToMany(cascade = CascadeType.ALL)                                   
	private List<Orders> orders = new ArrayList<>();

	public Customer(String customerName, String customerEmail, String userName, String userPassword,
			Address userAddress) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAddress = userAddress;
	}
	
	
	
	
}
