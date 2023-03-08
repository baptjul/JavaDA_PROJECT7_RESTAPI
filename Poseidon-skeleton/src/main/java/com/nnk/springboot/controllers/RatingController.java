package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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
public class RatingController {
    // TODO: Inject Rating service
    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> ratings = ratingService.getAllRatings();
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (result.hasErrors()) {
            model.addAttribute("rating", rating);
            return "curvePoint/add";
        }
        Rating newRating = ratingService.saveRating(rating);
        model.addAttribute("rating", newRating);
        return this.home(model);
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) throws IllegalAccessException {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            model.addAttribute("rating", rating);
            return "redirect:/rating/list";
        }

        Rating updatedCRating = ratingService.updateRating(id, rating);
        model.addAttribute("rating", updatedCRating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        List<Rating> ratings = ratingService.deleteRating(id);
        model.addAttribute("ratings", ratings);
        return "redirect:/rating/list";
    }
}
