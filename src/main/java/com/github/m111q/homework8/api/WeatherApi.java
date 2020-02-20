package com.github.m111q.homework8.api;

import com.github.m111q.homework8.model.CityTempInfo;
import com.github.m111q.homework8.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherApi {

    private WeatherService service;

    @Autowired
    public WeatherApi(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CityTempInfo>> getAllWeatherInfo() {
        List<CityTempInfo> weatherInfo = service.getAllWeatherInfo();
        if (!weatherInfo.isEmpty()) {
            return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{city}")
    public ResponseEntity setTrackedCity(@PathVariable String city) {
        service.setCity(city);
        return new ResponseEntity(HttpStatus.OK);
    }

}
