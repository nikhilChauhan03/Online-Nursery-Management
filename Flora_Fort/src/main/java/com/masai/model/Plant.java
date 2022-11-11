package com.masai.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantId;
	
	private Integer plantHeight;
	
	@NotNull(message = "commonName should not be Null")
	@NotBlank(message = "commonName should not blank")
	private String commonName;
	
	@NotNull(message = "bloom should not be Null")
	@NotBlank(message = "bloom should not blank")
	private String bloom;
	
	@NotNull(message = "useOfPlant should not be Null")
	@NotBlank(message = "useOfPlant should not blank")
	private String useOfPlant;
	
	@NotNull(message = "difficultyLevel should not be Null")
	@NotBlank(message = "difficultyLevel should not blank")
	private String difficultyLevel;
	
	@NotNull(message = "temp should not be Null")
	@NotBlank(message = "temp should not blank")
	private String temp;
	
	@NotNull(message = "typeOfPlant should not be Null")
	@NotBlank(message = "typeofPlant should not blank")
	private String typeOfPlant;
	
	@NotNull(message = "Description should not be Null")
	@NotBlank(message = "Description should not blank")
	private String description;
	
	@NotNull(message = "plantStock should not be Null")
	@NotBlank(message = "plantStock should not blank")
	private Integer plantStock;
	
	@NotNull(message = "cost should not be Null")
	@NotBlank(message = "cost should not blank")
	private Integer cost;
	

	
	
	

}
