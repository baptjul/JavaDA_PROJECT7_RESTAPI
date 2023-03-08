package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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

@Controller
public class TradeController {
    // TODO: Inject Trade service
    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<Trade> allTrades = tradeService.getAllTrades();
        model.addAttribute("trades", allTrades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Model model) {
        Trade newTrade = new Trade();
        model.addAttribute("trade", newTrade);
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (result.hasErrors()) {
            model.addAttribute("trade", trade);
            return "trade/add";
        }
        Trade newTrade = tradeService.saveTrade(trade);
        model.addAttribute("trade", newTrade);
        return  this.home(model);
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            model.addAttribute("trade", trade);
            return "trade/add";
        }

        Trade updatedTrade = tradeService.saveTrade(trade);
        model.addAttribute("trade", updatedTrade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        List<Trade> trades = tradeService.deleteTrade(id);
        model.addAttribute("trades", trades);
        return "redirect:/trade/list";
    }
}
