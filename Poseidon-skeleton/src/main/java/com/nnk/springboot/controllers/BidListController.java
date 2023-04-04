package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller requests related to the {@link BidList} entity
 */
@Controller
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * GET request for all bids
     * Retrieves a list of all BidList and add them to the model for the view
     * @param model model object
     * @return page of the list of bids
     */
    @GetMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidList = bidListService.getAllBids();
        model.addAttribute("bidList", bidList);
        return "bidList/list";
    }

    /**
     * GET request to add a new bid
     * Creates a new BidList and add it to the model for the view
     * @param model model object
     * @return page to add bids
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        BidList newBidList = new BidList();
        model.addAttribute("bidList", newBidList);
        return "bidList/add";
    }

    /**
     * POST request for adding a bid
     * @param bid BidList object
     * @param result result of validation
     * @param model model object
     * @return page of the list of bids or page to add bids
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bidList", bid);
            return "bidList/add";
        }
        BidList newBidList = bidListService.addBidForm(bid);
        model.addAttribute("bidList", newBidList);
        return this.home(model);
    }

    /**
     * GET request to update a bid
     * @param id id of the BidList to update
     * @param model model object
     * @return page to update bids
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList targetBid = bidListService.getBidById(id);
        model.addAttribute("bidList", targetBid);
        return "bidList/update";
    }

    /**
     * POST request for updating a bid
     * @param id id of the BidList to update
     * @param bidList BidList object
     * @param result result of validation
     * @param model model object
     * @return page of the list of bids or page to update bids
     * @throws IllegalAccessException if BidList can't be updated
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) throws IllegalAccessException {
        if (result.hasErrors()) {
            model.addAttribute("bidList", bidList);
            return "curvePoint/update";
        }

        BidList updatedBid = bidListService.updateBid(id, bidList);
        model.addAttribute("bidList", updatedBid);
        return "redirect:/bidList/list";
    }

    /**
     * POST request for deleting a bid
     * @param id id of the BidList to delete
     * @param model model object
     * @return page of the list of bids
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        List<BidList> bidList = bidListService.deleteBid(id);
        model.addAttribute("bidList", bidList);
        return "redirect:/bidList/list";
    }
}
