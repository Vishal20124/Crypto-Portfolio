package com.bridgelabz.dto;

import jakarta.validation.constraints.*;

public class AlertRequest {

    @NotBlank(message = "Symbol is required")
    private String symbol;

    @DecimalMin(value = "0.0", inclusive = false, message = "Trigger price must be greater than 0")
    private double triggerPrice;

    @Pattern(regexp = "above|below", message = "Direction must be 'above' or 'below'")
    private String direction;

    @NotNull(message = "User ID is required")
    private Long userId;

    // Getters and setters
}
