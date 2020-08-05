package com.example.fizzbuzz.service;

import com.example.fizzbuzz.config.RulesConfigModel;
import com.example.fizzbuzz.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RulesConfigReaderService {

    @Value("${providers.config.file}")
    private String providersConfigFileName;

    private Map<Integer, String> rulesMap;


    public void loadConfig() {
        rulesMap = new HashMap<>();

        List<RulesConfigModel> allRules = Utils.fromFileToList(RulesConfigService.class.getClassLoader().getResource(providersConfigFileName).getPath(), new TypeReference<List<RulesConfigModel>>() {
        });

        fillUpMapWithRules(0, allRules);
    }

    private void fillUpMapWithRules(int index, List<RulesConfigModel> allRules) {
        if (index >= allRules.size()) {
            return;
        }
        RulesConfigModel currentRule = allRules.get(index);
        if (currentRule.getDividableBy() <= 0) {
            throw new IllegalArgumentException("Config contains invalid arguments! Rule Index: " + currentRule.getRuleId());
        }
        rulesMap.put(currentRule.getDividableBy(), currentRule.getRuleResult());
        fillUpMapWithRules(index + 1, allRules);
    }

    public Map getRules() {
        return rulesMap;
    }

}
