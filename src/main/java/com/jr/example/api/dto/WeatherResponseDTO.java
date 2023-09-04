package com.jr.example.api.dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

class HourlyUnits {
    @Getter @Setter private String time;
    @Getter @Setter private String temperature_2m;
}

class HourlyData {
    @Getter @Setter private List<String> time;
    @Getter @Setter private List<String> temperature_2m;
}

public class WeatherResponseDTO {
    @Getter @Setter private Double latitude;
    @Getter @Setter private Double longitude;
    @Getter @Setter private Double generationtime_ms;
    @Getter @Setter private Integer utc_offset_seconds;
    @Getter @Setter private String timezone;
    @Getter @Setter private String timezone_abbreviation;
    @Getter @Setter private Integer elevation;
    @Getter @Setter private HourlyUnits hourly_units;
    @Getter @Setter private HourlyData hourly;
}
