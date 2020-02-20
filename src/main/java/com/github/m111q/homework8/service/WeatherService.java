package com.github.m111q.homework8.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.m111q.homework8.model.CityTempInfo;
import com.github.m111q.homework8.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;

@Service
public class WeatherService {

    private static final String KEY = "APPID=e4ca033fa0ee55203d5de8891c986cc8";
    private static final String UNITS_METRIC = "units=metric";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String LANG_PL = "lang=pl";
    private static final String DEFAULT_CITY = "Gdansk";
    private WeatherRepository weatherRepository;
    private String city;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        city = DEFAULT_CITY;
    }

    public void setCity(String city) {
        //TODO check is city exist
        this.city = city;
    }

    public CityTempInfo getTempFromApi(String city) {
        String url = getUrl(city);
        Double temp = getTemp(url);
        String measurementTime = LocalTime.now().toString();

        return new CityTempInfo(city, temp, measurementTime);
    }

    @Scheduled(fixedDelay = (1000*15))//(1000 * 60 * 60))
    public void saveWeatherInfoOncePerHour() {
        weatherRepository.save(getTempFromApi(city));
    }

    public List<CityTempInfo> getAllWeatherInfo() {
        return weatherRepository.findAll();
    }

    private double getTemp(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, JsonNode.class).get("main").get("temp").asDouble();
    }

    private String getUrl(String city) {
        return API_URL + city + "&" + KEY + "&" + UNITS_METRIC + "&" + LANG_PL;
    }


}
