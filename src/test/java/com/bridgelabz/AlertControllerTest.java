package com.bridgelabz;

import com.bridgelabz.controller.AlertController;
import com.bridgelabz.entity.Alert;
import com.bridgelabz.service.AlertService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AlertControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alertController).build();
    }

    @Test
    public void testCreateAlert() throws Exception {
        Alert alert = new Alert();
        alert.setUserId(2);
        alert.setSymbol("SOL");
        alert.setTriggerPrice(90.0);
        alert.setDirection("BELOW");

        when(alertService.createAlert(any(Alert.class))).thenReturn(alert);

        mockMvc.perform(post("/alerts/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(alert)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value("SOL"));
    }

    @Test
    public void testGetMyAlerts() throws Exception {
        Alert alert = new Alert();
        alert.setUserId(2);

        when(alertService.getAlertsByUserId(2L)).thenReturn(List.of(alert));

        mockMvc.perform(get("/alerts/my").param("userId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(2));
    }

    @Test
    public void testGetTriggeredAlerts() throws Exception {
        Alert alert = new Alert();
        alert.setStatus("triggered");

        when(alertService.getTriggeredAlerts()).thenReturn(List.of(alert));

        mockMvc.perform(get("/alerts/triggered"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("triggered"));
    }
}

