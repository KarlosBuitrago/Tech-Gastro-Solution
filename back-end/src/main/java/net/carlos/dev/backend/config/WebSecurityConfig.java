package net.carlos.dev.backend.config;

import net.carlos.dev.backend.config.model.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{
	
	@Autowired
	JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.csrf((csrf) -> csrf
						.disable())
					.authorizeHttpRequests(authz -> authz
					.requestMatchers(Constants.LOGIN_URL).permitAll()
					.requestMatchers( "/api-docs/**").permitAll()
					.requestMatchers("/swagger-ui/**").permitAll()
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//					 .anyRequest().permitAll());
					// all other requests need to be authenticated
					.anyRequest().authenticated())
					.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}