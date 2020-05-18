package com.concirrus.service;

import com.concirrus.model.Circle;
import org.springframework.stereotype.Service;

@Service
public class AreaCalculation {

    public double calculateAreaOfCircle (final Circle circle) {
        return Math.pow(circle.getRadius(),2)*Math.PI;
    }
}
