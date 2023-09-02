package com.jr.example.api.dto;

import java.util.Optional;

public class LocationRequest {
    private double latitude;
    private double longitude;
    private String hourly;
    // private Optional<String> daily;
    private Optional<String> timezone;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }


    public Optional<String> getTimezone() {
        return timezone;
    }

    public void setTimezone(Optional<String> timezone) {
        this.timezone = timezone;
    }

    // public Optional<String> getTemperature_units() {
    //     return temperature_units;
    // }

    // public void setTemperature_units(Optional<String> temperature_units) {
    //     this.temperature_units = temperature_units;
    // }
}
