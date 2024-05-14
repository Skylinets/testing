package junit5

class TemperatureCalculator {

    fun toFahrenheit(degree: Float): Float {
        val fahrenheit = (degree * 9 / 5) + 32
        return fahrenheit
    }
}