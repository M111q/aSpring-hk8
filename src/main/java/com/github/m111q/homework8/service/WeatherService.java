package com.github.m111q.homework8.service;

import com.github.m111q.homework8.model.WeatherInfo;
import com.github.m111q.homework8.model.WeatherInfoDTO;
import com.github.m111q.homework8.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {

    private static final String KEY = "APPID=e4ca033fa0ee55203d5de8891c986cc8";
    private static final String UNITS_METRIC = "units=metric";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String LANG_PL = "lang=pl";
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherInfoDTO getWeatherInfoDTO(String city) {
        return new WeatherInfoDTO(getWeatherInfoFromApi(city));
    }

    private WeatherInfo getWeatherInfoFromApi(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + city + "&" + KEY + "&" + UNITS_METRIC + "&" + LANG_PL;
        WeatherInfo weatherInfo = restTemplate.getForObject(url,
                WeatherInfo.class);
        return weatherInfo;
    }

    @Scheduled(fixedDelay = (1000*60*10))
    public void start() {
        WeatherInfoDTO chojnice = this.getWeatherInfoDTO("Chojnice");
        weatherRepository.save(chojnice);

        List<WeatherInfoDTO> all = weatherRepository.findAll();
        all.forEach(System.out::println);
    }

}
