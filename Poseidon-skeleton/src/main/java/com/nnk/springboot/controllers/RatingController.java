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

/**
 * Controller requests related to the {@link Rating} entity.
 */
@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * request for all ratings
     * Retrieves a list of all ratings and add them to the model for the view
     * @param model model object
     * @return page of the list of ratings
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        List<Rating> ratings = ratingService.getAllRatings();
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    /**
     * GET request to add a new rating
     * Creates a new rating and add it to the model for the view
     * @param model model object
     * @return page to add rating
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        return "rating/add";
    }

    /**
     * POST request for adding a rating
     * @param rating rating object
     * @param result result of validation
     * @param model model object
     * @return page of the list of ratings or page to add rating
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("rating", rating);
            return "curvePoint/add";
        }
        Rating newRating = ratingService.saveRating(rating);
        model.addAttribute("rating", newRating);
        return this.home(model);
    }

    /**
     * GET request to update a rating
     * @param id id of the rating to update
     * @param model model object
     * @return page to update rating
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * POST request for updating a rating
     * @param id id of the curvePoint to update
     * @param rating rating object
     * @param result result of validation
     * @param model model object
     * @return page of the list of ratings or page to update rating
     * @throws IllegalAccessException if rating can't be updated
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) throws IllegalAccessException {
        if (result.hasErrors()) {
            model.addAttribute("rating", rating);
            return "redirect:/rating/list";
        }

        Rating updatedCRating = ratingService.updateRating(id, rating);
        model.addAttribute("rating", updatedCRating);
        return "redirect:/rating/list";
    }

    /**
     * POST request for deleting a rating
     * @param id id of the rating to delete
     * @param model model object
     * @return page of the list of ratings
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        List<Rating> ratings = ratingService.deleteRating(id);
        model.addAttribute("ratings", ratings);
        return "redirect:/rating/list";
    }
}
