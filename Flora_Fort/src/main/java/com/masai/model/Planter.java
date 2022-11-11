package com.masai.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planterId;
	
	@Min(value = 1, message = "Planter's Height can't be 0")
	private Float planterheight;
	
	@Min(value = 1, message = "Planter's Capacity can't be 0")
	private Integer planterCapacity;
	
	@Min(value = 1, message = "Planter's Drinage Hole can't be 0")
	private Integer drinageHoles;
	
	@NotBlank(message = "Planter's color can't be blank")
	private String planterColor;
	
	@NotBlank(message = "Planter's shape can't be blank")
	private String planterShape;
	
	@Min(value = 0, message = "Planter's stock can't be negative")
	private Integer planterStock;
	
	@Min(value = 1, message = "Planter's cost can't be 0")
	private Integer planterCost;

	public Integer getPlanterId() {
		return planterId;
	}

	public void setPlanterId(Integer planterId) {
		this.planterId = planterId;
	}

	public Float getPlanterheight() {
		return planterheight;
	}

	public void setPlanterheight(Float planterheight) {
		this.planterheight = planterheight;
	}

	public Integer getPlanterCapacity() {
		return planterCapacity;
	}

	public void setPlanterCapacity(Integer planterCapacity) {
		this.planterCapacity = planterCapacity;
	}

	public Integer getDrinageHoles() {
		return drinageHoles;
	}

	public void setDrinageHoles(Integer drinageHoles) {
		this.drinageHoles = drinageHoles;
	}

	public String getPlanterColor() {
		return planterColor;
	}

	public void setPlanterColor(String planterColor) {
		this.planterColor = planterColor;
	}

	public String getPlanterShape() {
		return planterShape;
	}

	public void setPlanterShape(String planterShape) {
		this.planterShape = planterShape;
	}

	public Integer getPlanterStock() {
		return planterStock;
	}

	public void setPlanterStock(Integer planterStock) {
		this.planterStock = planterStock;
	}

	public Integer getPlanterCost() {
		return planterCost;
	}

	public void setPlanterCost(Integer planterCost) {
		this.planterCost = planterCost;
	}
	
	



//private List<Plant> plants;
//
//
//private List<Seed> seeds;

}
