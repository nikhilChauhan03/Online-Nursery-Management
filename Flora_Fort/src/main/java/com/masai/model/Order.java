package com.masai.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer BookingOrderId;
	@NotNull
	@DateTimeFormat
	private LocalDate Oderdate;
	@NotNull
	private String TransactionMode;
	@NotNull
	@Size(min =1)
	private Integer Quantity;
	@NotNull
	private double TotalCost;
    //private Planters planters;

}
