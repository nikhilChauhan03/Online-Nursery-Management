package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
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

@Data
@NoArgsConstructor
@Entity
public class CurrentUserSession {	
	
	@Id
	@Column(unique = true)
	@NotNull(message = "userName should not be Null")
	@NotBlank(message = "userName should not blank")
	@Size(min=4, message = "userName minimum length should be 4")
	private String userName;
	
	@NotNull(message = "sessionKey should not be Null")
	@NotBlank(message = "sessionKey should not blank")
	private String sessionKey;
	
	private LocalDateTime localDateTime;
	
	
	@NotNull(message = "typeOfUser should not be Null")
	@NotBlank(message = "typeOfUser should not blank")
	private String typeOfUser;


	public CurrentUserSession(
			@NotNull(message = "userName should not be Null") @NotBlank(message = "userName should not blank") @Size(min = 4, message = "userName minimum length should be 4") String userName,
			@NotNull(message = "sessionKey should not be Null") @NotBlank(message = "sessionKey should not blank") String sessionKey,
			 LocalDateTime localDateTime,
			@NotNull(message = "typeOfUser should not be Null") @NotBlank(message = "typeOfUser should not blank") String typeOfUser) {
		super();
		this.userName = userName;
		this.sessionKey = sessionKey;
		this.localDateTime = localDateTime;
		this.typeOfUser = typeOfUser;
	}
	
	
	
	
}
