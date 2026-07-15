package com.sana.rentora.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sana.rentora.config.JwtUtil;
import com.sana.rentora.dto.AuthRequestDTO;
import com.sana.rentora.dto.AuthResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authManager;
	
	private final JwtUtil jwt;
	
	public AuthController(AuthenticationManager authManager, JwtUtil jwt) {
		this.authManager=authManager;
		this.jwt=jwt;
	}
	
	@PostMapping("/login")
	public AuthResponseDTO  login(@RequestBody AuthRequestDTO authReq){
		
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authReq.getUsername(),authReq.getPassword())
				);
		
		String token = jwt.generateToken(authReq.getUsername());
		
		return new AuthResponseDTO(token);
	}
}
