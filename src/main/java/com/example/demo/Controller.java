package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(value = "/cpiData", method = RequestMethod.GET)
    public String getCPIData() {
        String cpiApiUrl = "https://api.bls.gov/publicAPI/v2/timeseries/data/CUUR0000SA0L1E";
        ResponseEntity<String> response = restTemplate.getForEntity(cpiApiUrl, String.class);
        String body = response.getBody();
        MediaType contentType = response.getHeaders().getContentType();
        System.out.println(contentType);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println(statusCode);
        return body;
    }
}
