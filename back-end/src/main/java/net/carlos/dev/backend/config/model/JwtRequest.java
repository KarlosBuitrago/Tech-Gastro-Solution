package net.carlos.dev.backend.config.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	

}