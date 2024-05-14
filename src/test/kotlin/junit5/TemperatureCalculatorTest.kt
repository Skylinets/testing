package junit5

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class TemperatureCalculatorTest {

    var temperatureCalculator: TemperatureCalculator? = null

    @BeforeEach
    fun setUp(){
        temperatureCalculator = TemperatureCalculator()
    }
    @AfterEach
    fun tearDown(){
        temperatureCalculator = null
    }
    @Test
    fun toFahrenheit() {
        val celsius = temperatureCalculator?.toFahrenheit(2f)
        val expected = 36f
        assertEquals(expected, celsius ?: 0f, 0.5f)
    }
}