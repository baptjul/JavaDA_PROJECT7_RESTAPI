package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller requests related to the {@link RuleName} entity.
 */
@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * request for all ruleNames
     * Retrieves a list of all ruleNames and add them to the model for the view
     * @param model model object
     * @return page of the list of ruleNames
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        List<RuleName> ruleNames = ruleNameService.getAllRuleNames();
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    /**
     * GET request to add a new ruleName
     * Creates a new ruleName and add it to the model for the view
     * @param model model object
     * @return page to add ruleName
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        RuleName ruleName = new RuleName();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/add";
    }

    /**
     * POST request for adding a ruleName
     * @param ruleName ruleName object
     * @param result result of validation
     * @param model model object
     * @return page of the list of ruleNames or page to add ruleName
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ruleName", ruleName);
            return "ruleName/add";
        }
        RuleName newRuleName = ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleName", newRuleName);
        return this.home(model);
    }

    /**
     * GET request to update a ruleName
     * @param id id of the ruleName to update
     * @param model model object
     * @return page to update ruleName
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * POST request for updating a ruleName
     * @param id id of the ruleName to update
     * @param ruleName rating object
     * @param result result of validation
     * @param model model object
     * @return page of the list of ruleNames or page to update ruleName
     * @throws IllegalAccessException if ruleName can't be updated
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) throws IllegalAccessException {
        if (result.hasErrors()) {
            model.addAttribute("ruleName", ruleName);
            return "redirect:/ruleName/list";
        }

        RuleName updateRuleName = ruleNameService.updateRuleName(id, ruleName);
        model.addAttribute("ruleName", updateRuleName);
        return "redirect:/ruleName/list";
    }

    /**
     * POST request for deleting a rulename
     * @param id id of the rulename to delete
     * @param model model object
     * @return page of the list of rulenames
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        List<RuleName> ruleName = ruleNameService.deleteRuleName(id);
        model.addAttribute("ruleName", ruleName);
        return "redirect:/ruleName/list";
    }
}
