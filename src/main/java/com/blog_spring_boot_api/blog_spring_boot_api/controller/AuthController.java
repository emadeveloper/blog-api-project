package com.blog_spring_boot_api.blog_spring_boot_api.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.LoginDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.dto.RegisterDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Rol;
import com.blog_spring_boot_api.blog_spring_boot_api.model.User;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.RolRepository;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.UserRepository;
import com.blog_spring_boot_api.blog_spring_boot_api.security.JwtAuthResponseDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RolRepository rolRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @PostMapping("/login")
        public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JwtAuthResponseDTO(token));
        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
            if(userRepository.existsByUsername(registerDTO.getUsername())){
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }

            if(userRepository.existsByEmail(registerDTO.getEmail())){
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            }

            User user = new User();
            user.setName(registerDTO.getName());
            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            Rol roles = rolRepository.findByName("ROLE_USER").get();
            user.setRoles(Collections.singleton(roles));

            userRepository.save(user);

            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
}
