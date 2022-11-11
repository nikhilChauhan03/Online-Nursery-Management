package com.masai.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	@NotNull(message = "userName should not be Null")
	@NotBlank(message = "userName should not blank")
	@Size(min=4, message = "userName minimum length should be 4")
	private String UserName;
	
	@Size(min=3, message = "Minimum userPassword length shoudl be 6")
	@NotNull(message = "userPassword should not be Null")
	@NotBlank(message = "userPassword should not blank")
	private String password;
	
	@NotNull(message = "typeOfUser should not be Null")
	@NotBlank(message = "typeOfUser should not blank")
	private String typeOfUser;
	
}
