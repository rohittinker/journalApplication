package com.rohitEnterprises.firstSuccessfullAPI.service;


import com.rohitEnterprises.firstSuccessfullAPI.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey ="cc374d1b6400f878e8da8e55333b665a";
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather (String city){
        String finalAPI = API.replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WeatherResponse>response =restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
