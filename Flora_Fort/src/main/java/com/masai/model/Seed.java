package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Valid
public class Seed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seedId;
	
	@NotNull(message = "Common Name can not be null...!")
	private String commonName;
	
	@NotEmpty(message = "Bloom Time should be there...!")
	private String bloomTime;
	
//	@NotEmpty(message = "Water facility can not be empty...!")
	private String Watering;
	
	@NotEmpty(message = "Difficulty Level can not be empty...!")
	private String difficultyLevel;
	
//	@NotBlank(message = "Temperature can not be blank...!")
	private String Temperature;
	
	@NotBlank(message = "Type of Seeds can not be blank...!")
	private String typeOFSeeds;
	
	private String seedsDescription;
	
	@NotNull(message = "Seeds Stock can not be null...!")
	private Integer seedsStock;
	
	@NotNull(message = "Seeds Cost can not be null...!")
	private double seedsCost;
	
	@NotNull(message = "Seeds Per Packet can not be null...!")
	private Integer seedsPerPacket;
	
	
}
