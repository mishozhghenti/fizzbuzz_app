package com.example.fizzbuzz.controllers;

import com.example.fizzbuzz.entity.User;
import com.example.fizzbuzz.payload.request.LoginRequest;
import com.example.fizzbuzz.payload.request.SignUpRequest;
import com.example.fizzbuzz.payload.response.JwtResponse;
import com.example.fizzbuzz.repository.UserRepository;
import com.example.fizzbuzz.security.jwt.JwtUtils;
import com.example.fizzbuzz.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            userRepository.save(new User(signUpRequest.getFullName(),
                    signUpRequest.getUsername(),
                    signUpRequest.getRole(),
                    encoder.encode(signUpRequest.getPassword())));
            return new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("UserName is already in use!", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
