package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public RuleName getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName updateRuleName(Integer id, RuleName updatedRuleName) throws IllegalAccessException {
        RuleName targetedRuleName = getRuleNameById(id);
        if (updatedRuleName != null) {
            Field[] fields = RuleName.class.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object newValue = field.get(updatedRuleName);
                if (newValue != null) {
                    field.set(targetedRuleName, newValue);
                }
            }

            return ruleNameRepository.save(targetedRuleName);
        } else {
            return updatedRuleName;
        }
    }

    public List<RuleName> deleteRuleName(Integer id) {
        RuleName targetedRule = getRuleNameById(id);
        if (targetedRule != null) {
            ruleNameRepository.deleteById(id);
        }
        return getAllRuleNames();
    }
}
