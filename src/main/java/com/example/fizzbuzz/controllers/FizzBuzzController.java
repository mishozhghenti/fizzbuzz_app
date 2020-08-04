package com.example.fizzbuzz.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fizzbuzz")
public class FizzBuzzController {

    @GetMapping("/evaluation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity addBookmark(Authentication authentication) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
