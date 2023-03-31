package com.nnk.springboot.controllers;

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

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
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
        if (result.hasErrors()) {
            model.addAttribute("trade", trade);
            return "trade/add";
        }
        Trade newTrade = tradeService.saveTrade(trade);
        model.addAttribute("trade", newTrade);
        return this.home(model);
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
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
        List<Trade> trades = tradeService.deleteTrade(id);
        model.addAttribute("trades", trades);
        return "redirect:/trade/list";
    }
}
