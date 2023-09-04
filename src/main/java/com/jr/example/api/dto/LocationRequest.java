package com.jr.example.api.dto;

import lombok.Getter;
import lombok.Setter;

public class LocationRequest {
    @Getter @Setter private Double latitude;
    @Getter @Setter private Double longitude;
}
