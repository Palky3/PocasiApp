package com.example.pocasiapp.models

class WeatherData {

    private val cityName: String? = null
    private val temperature: String? = null
    private val weatherDescription: String? = null
    private val icon: String? = null

    fun getCityName(): String? {
        return cityName
    }

    fun getTemperature(): String? {
        return temperature
    }

    fun getWeatherDescription(): String? {
        return weatherDescription
    }

    fun getIcon(): String? {
        return icon
    }
}