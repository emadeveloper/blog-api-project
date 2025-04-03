package com.blog_spring_boot_api.blog_spring_boot_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.LoginDTO;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>( "Login successful", HttpStatus.OK);
        }
}
