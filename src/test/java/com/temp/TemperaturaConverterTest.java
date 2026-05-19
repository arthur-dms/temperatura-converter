package com.temp;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperaturaConverterTest {
    @Test
    public void testFahrenheitParaCelsius() {
        assertEquals(100.0, TemperaturaConverter.fahrenheitParaCelsius(212.0), 0.01);
    }
    @Test
    public void testCelsiusParaFahrenheit() {
        assertEquals(212.0, TemperaturaConverter.celsiusParaFahrenheit(100.0), 0.01);
    }
}
