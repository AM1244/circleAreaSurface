package com.concirrus.controller;


import com.concirrus.model.Circle;
import com.concirrus.service.AreaCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Pricing controller class.
 */
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class Controller {


    private AreaCalculation areaCalculation;

    @Autowired
    public Controller(final AreaCalculation areaCalculation) {
        this.areaCalculation = areaCalculation;

    }
    /**
     * <p>
     * Post Method to Calculate the surface area of a circle given the radius.
     * @return Circle object
     */
    @PostMapping("/area")
    public Circle calculateAreaOfCircle (@RequestBody @Valid final Circle circle) {


        circle.setArea(areaCalculation.calculateAreaOfCircle(circle));
        return circle;
    }

}
