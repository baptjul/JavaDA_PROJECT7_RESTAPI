package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {
    // TODO: Inject Bid service
    @Autowired
    private BidListService bidListService;

    @GetMapping("/bidList/list")
    public String home(Model model) {
        // TODO: call service find all bids to show to the view
        List<BidList> bidList = bidListService.getAllBids();
        model.addAttribute("bidList", bidList);
        return "bidList/list";
    }

    // Modification
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        BidList newBidList = new BidList();
        model.addAttribute("bidList", newBidList);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            BidList newBidList = bidListService.addBidForm(bid);
            model.addAttribute("bidList", newBidList);
            return this.home(model);
        } else {
            model.addAttribute("bidList", bid);
            return "bidList/add";
        }
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        BidList targetBid = bidListService.getBidById(id);
        model.addAttribute("bidList", targetBid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) throws IllegalAccessException {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            model.addAttribute("bidList", bidList);
            return "curvePoint/update";
        }

        BidList updatedBid = bidListService.updateBid(id, bidList);
        model.addAttribute("bidList", updatedBid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        List<BidList> bidList = bidListService.deleteBid(id);
        model.addAttribute("bidList", bidList);
        return "redirect:/bidList/list";
    }
}
