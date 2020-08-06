package com.example.fizzbuzz.controllers;

import com.example.fizzbuzz.service.RulesEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fizzbuzz")
public class FizzBuzzController {

    @Autowired
    RulesEvaluationService rulesEvaluationService;

    @GetMapping("/evaluation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity evaluationFizzBuzz() {
        return new ResponseEntity(rulesEvaluationService.evaluate(), HttpStatus.OK);
    }
}
