package com.example.fizzbuzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RulesConfigService {

    @Autowired
    private RulesConfigReaderService rulesConfigReaderService;


    @PostConstruct
    void init() {
        rulesConfigReaderService.loadConfig();
    }

    public RulesConfigReaderService getRulesConfigReaderService() {
        return rulesConfigReaderService;
    }
}
