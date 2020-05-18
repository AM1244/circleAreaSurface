package com.concirrus.controller;

import com.concirrus.model.Circle;
import com.concirrus.service.AreaCalculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControllerTest {
    public MockMvc mockMvc;

    @Mock
    private AreaCalculation areaCalculation;

    private Controller controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(areaCalculation);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test_When_ValidRequest_Expect_Success() throws Exception {
        double area = 1d;
        when(areaCalculation.calculateAreaOfCircle(any(Circle.class))).thenReturn(area);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto(1d)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(areaCalculation).calculateAreaOfCircle(any(Circle.class));
    }

    @Test
    public void test_When_WrongTypeOfFieldIsPresentInRequest_Expect_BadRequest() throws Exception {
        double area = 1d;
        when(areaCalculation.calculateAreaOfCircle(any(Circle.class))).thenReturn(area);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto(-1)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verifyZeroInteractions(areaCalculation);
    }

    @Test
    public void test_When_PostUrlIsWrong_Expect_NotFound() throws Exception {
        double area = 1d;
        when(areaCalculation.calculateAreaOfCircle(any(Circle.class))).thenReturn(area);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/wrongUrl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto(1d)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verifyZeroInteractions(areaCalculation);
    }

    @Test
    public void test_When_MandatoryFieldIsMissingFromRequest_Expect_BadRequest() throws Exception {

        double area = 1d;
        when(areaCalculation.calculateAreaOfCircle(any(Circle.class))).thenReturn(area);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDtoWrong(1d)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verifyZeroInteractions(areaCalculation);

    }

    private Circle requestDtoWrong(double area) {
        Circle circle = new Circle();
        circle.setArea(area);

        return circle;
    }

    private String asJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    private Circle requestDto(double radius) {
        Circle circle = new Circle();
        circle.setRadius(radius);

        return circle;
    }
}






//public class PricingControllerTest {
//
//    public MockMvc mockMvc;
//
//    @Mock
//    private PricingService pricingService;
//
//    private PricingController pricingController;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//        pricingController = new PricingController(pricingService);
//        mockMvc = MockMvcBuilders.standaloneSetup(pricingController).build();
//    }
//
//    @Test
//    public void test_When_ValidRequest_Expect_Success() throws Exception {
//
//        when(pricingService.retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class))).thenReturn(productPricingResponseDto("SEAT"));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/programmes/programme-id/product-prices")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(productPricingRequestDto("SEAT","GBP")))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        verify(pricingService).retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class));
//
//    }
//
//    @Test
//    public void test_When_MandatoryFieldIsMissingFromRequest_Expect_BadRequest() throws Exception {
//
//        when(pricingService.retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class))).thenReturn(productPricingResponseDto("SEAT"));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/programmes/programme-id/product-prices")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(flightAncillaryDto("SEAT")))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//
//        verifyZeroInteractions(pricingService);
//
//    }
//
//    @Test
//    public void test_When_PostUrlIsWrong_Expect_NotFound() throws Exception {
//
//        when(pricingService.retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class))).thenReturn(productPricingResponseDto("SEAT"));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/programmes/programme-id")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(productPricingRequestDto("SEAT", "GBP")))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//
//        verifyZeroInteractions(pricingService);
//    }
//
//    @Test
//    public void test_When_ValidationErrorOccursInRequest_Expect_BadRequest() throws Exception {
//
//        when(pricingService.retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class))).thenReturn(productPricingResponseDto("SEAT"));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/programmes/programme-id/product-prices")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(productPricingRequestDto("SEAT", "50")))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//
//        verifyZeroInteractions(pricingService);
//    }
//
//    @Test
//    public void test_When_WrongFieldIsPresentInRequest_Expect_BadRequest() throws Exception {
//
//        when(pricingService.retrieveProductPricingResponse(any(ProductPricingRequest.class), any(Map.class))).thenReturn(productPricingResponseDto("SEAT"));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/programmes/programme-id/product-prices")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(productPricingRequestDto("Wrong-Flight-Ancillary-Type", "GBP")))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest());
//
//        verifyZeroInteractions(pricingService);
//    }
//
//    private String asJsonString(final Object obj) throws JsonProcessingException {
//        final ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(obj);
//    }
//
//}
