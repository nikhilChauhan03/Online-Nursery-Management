package com.masai.DTO;

import com.masai.model.CurrentUserSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutDTO {

	private CurrentUserSession currentUserSession;
	private String message;
}
