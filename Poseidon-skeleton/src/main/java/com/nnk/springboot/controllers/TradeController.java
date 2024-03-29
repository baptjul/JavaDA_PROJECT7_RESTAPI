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

/**
 * Controller requests related to the {@link Trade} entity.
 */
@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * request for all trades
     * Retrieves a list of all trades and add them to the model for the view
     * @param model model object
     * @return page of the list of trades
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        List<Trade> allTrades = tradeService.getAllTrades();
        model.addAttribute("trades", allTrades);
        return "trade/list";
    }

    /**
     * GET request to add a new trade
     * Creates a new ruleName and add it to the model for the view
     * @param model model object
     * @return page to add trade
     */
    @GetMapping("/trade/add")
    public String addUser(Model model) {
        Trade newTrade = new Trade();
        model.addAttribute("trade", newTrade);
        return "trade/add";
    }

    /**
     * POST request for adding a ruleName
     * @param trade trade object
     * @param result result of validation
     * @param model model object
     * @return page of the list of trades or page to add trade
     */
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

    /**
     * GET request to update a trade
     * @param id id of the trade to update
     * @param model model object
     * @return page to update trade
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * POST request for updating a trade
     * @param id id of the trade to update
     * @param trade trade object
     * @param result result of validation
     * @param model model object
     * @return page of the list of trades or page to update trade
     * @throws IllegalAccessException if trade can't be updated
     */
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

    /**
     * POST request for deleting a trade
     * @param id id of the trade to delete
     * @param model model object
     * @return page of the list of trades
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        List<Trade> trades = tradeService.deleteTrade(id);
        model.addAttribute("trades", trades);
        return "redirect:/trade/list";
    }
}
