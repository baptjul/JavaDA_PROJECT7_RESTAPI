package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Service class for managing ruleName operations
 */
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Gets a list of all ruleName
     * @return the list of ruleName
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Gets a ruleName
     * @param id the ID of the ruleName
     * @return a single ruleName or null
     */
    public RuleName getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new ruleName
     * @param ruleName ruleName to add
     * @return the added ruleName
     */
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Updates a ruleName
     * @param id the ID of the ruleName
     * @param updatedRuleName the updated ruleName
     * @return the updated ruleName
     * @throws IllegalAccessException if updatedRuleName is null
     */
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

    /**
     * Deletes a ruleName
     * @param id the ID of the ruleName
     * @return the list of all ruleNames
     */
    public List<RuleName> deleteRuleName(Integer id) {
        RuleName targetedRule = getRuleNameById(id);
        if (targetedRule != null) {
            ruleNameRepository.deleteById(id);
        }
        return getAllRuleNames();
    }
}
