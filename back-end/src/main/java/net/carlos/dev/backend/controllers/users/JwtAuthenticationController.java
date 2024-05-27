package net.carlos.dev.backend.controllers.users;

import net.carlos.dev.backend.config.JWTAuthtenticationConfig;
import net.carlos.dev.backend.config.model.JwtRequest;
import net.carlos.dev.backend.config.model.JwtResponse;
import net.carlos.dev.backend.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	JWTAuthtenticationConfig jwtAuthtenticationConfig;
	@Autowired
	UserDetailsService jwtInMemoryUserDetailsService;
	@Qualifier("IUserService")
	@Autowired
	private IUserService userServiceImpl;

	@Operation(summary = "Crea un token de autenticación", description = "Genera un token JWT para autenticación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token creado exitosamente"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
	@RequestMapping(
			value = "/authenticate",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> createAuthenticationToken (@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("*****************************************************************************");
		System.out.println("authenticationRequest.getUsername():["+authenticationRequest.getUsername()+"]");
		System.out.println("authenticationRequest.getPassword():["+authenticationRequest.getPassword()+"]");
		System.out.println("*****************************************************************************");
		final UserDetails userDetails = userServiceImpl
				.loadUserByUsername(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final String token = jwtAuthtenticationConfig.getJWTToken(userDetails.getUsername());
		System.out.println("*****************************************************************************");
		System.out.println("token: ["+token+"]");
		System.out.println("*****************************************************************************");
		return ResponseEntity.ok(new JwtResponse(token));
	
	}
		
}