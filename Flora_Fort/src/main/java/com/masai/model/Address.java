package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@NotNull(message = "houseNo should not be Null")
	@NotBlank(message = "houseNo should not blank")
	private Integer houseNo;
	
	@NotNull(message = "colony should not be Null")
	@NotBlank(message = "colony should not blank")
	private String colony;
	
	@NotNull(message = "city should not be Null")
	@NotBlank(message = "city should not blank")
	private String city;
	
	@NotNull(message = "state should not be Null")
	@NotBlank(message = "state should not blank")
	private String state;
	
	@NotNull(message = "pincode should not be Null")
	@NotBlank(message = "pincode should not blank")
	private Integer pincode;
	
}
