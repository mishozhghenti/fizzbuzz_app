package com.example.fizzbuzz.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "rule_id",
        "rule_result",
        "dividable_by"
})
public class RulesConfigModel {
    @JsonProperty("rule_id")
    private Integer ruleId;
    @JsonProperty("rule_result")
    private String ruleResult;
    @JsonProperty("dividable_by")
    private Integer dividableBy;

    public RulesConfigModel() {
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleResult() {
        return ruleResult;
    }

    public void setRuleResult(String ruleResult) {
        this.ruleResult = ruleResult;
    }

    public Integer getDividableBy() {
        return dividableBy;
    }

    public void setDividableBy(Integer dividableBy) {
        this.dividableBy = dividableBy;
    }
}
