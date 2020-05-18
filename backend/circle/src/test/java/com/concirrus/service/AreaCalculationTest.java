package com.concirrus.service;

import com.concirrus.model.Circle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AreaCalculationTest {
    private AreaCalculation areaCalculation;

    @Before
    public void init() {
        areaCalculation = new AreaCalculation();
    }

    @Test
    public void test_When_AreaCalculation_Succeeds()  {
        Circle circle = new Circle();
        circle.setRadius(1d);

        Assert.assertEquals(3.141592653589793,areaCalculation.calculateAreaOfCircle(circle),1d );

    }

    @Test
    public void test_When_AreaCalculation_Fails()  {
        Circle circle = new Circle();
        circle.setRadius(1d);

        Double area = areaCalculation.calculateAreaOfCircle(circle);
        Assert.assertFalse(area.equals(3.14));

    }
}
