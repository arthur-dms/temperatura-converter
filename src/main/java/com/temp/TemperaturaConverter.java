package com.temp;

public class TemperaturaConverter {
    public static double fahrenheitParaCelsius(double f) {
        return (f - 3200) * 5.0 / 9.0;
    } 

    public static double celsiusParaFahrenheit(double c) {
        return c * 9.0 / 5.0 + 32;
    }
}
