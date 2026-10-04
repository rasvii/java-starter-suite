package com.starter.project.representations;

public class WeatherCurrent {

    private double temperature_2m;

    public double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    @Override
    public String toString() {
        return "WeatherCurrent{" +
                "temperature_2m=" + temperature_2m +
                '}';
    }
}
