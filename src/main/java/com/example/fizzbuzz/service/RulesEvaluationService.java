package com.example.fizzbuzz.service;

import com.example.fizzbuzz.constants.FizzBuzzConst;
import com.example.fizzbuzz.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class RulesEvaluationService {

    @Autowired
    RulesConfigService rulesConfigService;


    public List evaluate() {
        List<Object> evaluateResults = new ArrayList<>();
        evaluateRec(FizzBuzzConst.RANGE_FROM, rulesConfigService.getRulesConfigReaderService().getRules(), evaluateResults);
        return evaluateResults;
    }


    private void evaluateRec(int number, Map<Integer, String> rules, List<Object> result) {
        if (number > FizzBuzzConst.RANGE_TO) {
            return;
        }
        evaluateSingleNumber(number, rules, result);
        evaluateRec(number + 1, rules, result);
    }

    private void evaluateSingleNumber(int number, Map<Integer, String> rules, List<Object> result) {
        Iterator<Map.Entry<Integer, String>> it = rules.entrySet().iterator();
        evaluateSingleNumberRec(number, new BiggestDivider(), it, result);
    }


    private void evaluateSingleNumberRec(int number, BiggestDivider currentBiggestDivider, Iterator<Map.Entry<Integer, String>> it, List<Object> result) {
        if (!it.hasNext()) {
            result.add((currentBiggestDivider.result == null) ? number : currentBiggestDivider.result);
            return;
        }

        Map.Entry<Integer, String> pair = it.next();

        Integer dividable = pair.getKey();
        String ruleResult = pair.getValue();

        if (Utils.module(number, dividable) == 0) {
            if (currentBiggestDivider.number == null) {
                currentBiggestDivider.number = dividable;
                currentBiggestDivider.result = ruleResult;
            } else {
                if (currentBiggestDivider.number < dividable) {
                    currentBiggestDivider.number = dividable;
                    currentBiggestDivider.result = ruleResult;
                }
            }
        }
        evaluateSingleNumberRec(number, currentBiggestDivider, it, result);
    }

    private class BiggestDivider {
        Integer number;
        String result;
    }
}
