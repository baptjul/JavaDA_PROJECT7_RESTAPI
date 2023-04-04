package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurveService;
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
 * Controller requests related to the {@link CurvePoint} entity.
 */
@Controller
public class CurveController {
    @Autowired
    private CurveService curveService;

    /**
     * request for all curvePoint
     * Retrieves a list of all curvePoints and add them to the model for the view
     * @param model model object
     * @return page of the list of curvePoints
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        List<CurvePoint> curvePoints = curveService.getAllCurve();
        model.addAttribute("curvePoints", curvePoints);
        return "curvePoint/list";
    }

    /**
     * GET request to add a new curvePoint
     * Creates a new curvePoint and add it to the model for the view
     * @param model model object
     * @return page to add curvePoint
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {
        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    /**
     * POST request for adding a curvePoint
     * @param curvePoint curvePoint object
     * @param result result of validation
     * @param model model object
     * @return page of the list of curvePoints or page to add curvePoint
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/add";
        }
        CurvePoint newCurvePoint = curveService.saveCurvePoint(curvePoint);
        model.addAttribute("curvePoint", newCurvePoint);
        return this.home(model);
    }

    /**
     * GET request to update a curvePoint
     * @param id id of the curvePoint to update
     * @param model model object
     * @return page to update curvePoint
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curveService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * POST request for updating a curvePoint
     * @param id id of the curvePoint to update
     * @param curvePoint curvePoint object
     * @param result result of validation
     * @param model model object
     * @return page of the list of bids or page to update curvePoint
     * @throws IllegalAccessException if curvePoint can't be updated
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) throws IllegalAccessException {
        if (result.hasErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/update";
        }

        CurvePoint updatedCurvePoint = curveService.updateCurvePoint(id, curvePoint);
        model.addAttribute("curvePoint", updatedCurvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * POST request for deleting a curvePoint
     * @param id id of the curvePoint to delete
     * @param model model object
     * @return page of the list of curvePoint
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        List<CurvePoint> curvePoints = curveService.deleteCurvePoint(id);
        model.addAttribute("curvePoints", curvePoints);
        return "redirect:/curvePoint/list";
    }
}
