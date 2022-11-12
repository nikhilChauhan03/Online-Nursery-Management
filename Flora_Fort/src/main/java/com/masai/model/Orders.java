package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;
	
	@NotNull(message = "orderDate should not be null")
	@NotBlank(message = "orderDate should not be blank")
	private LocalDate orderDate;
	
	@NotNull(message = "transactionMode should not be null")
	@NotBlank(message = "transactionMode should not be blank")
	private String transactionMode;
	
	private Integer quantity;
	
	private Integer totalCost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Planter planters;
	
	
	
	
	
}
