package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }

    @RequestMapping(value = "/cpiData", method = RequestMethod.GET)
    public String getCPIData() {
        String cpiApiUrl = "https://api.bls.gov/publicAPI/v2/timeseries/data/CUUR0000SA0L1E";
        Root cpiResponse = restTemplate.getForObject(cpiApiUrl, Root.class);
        assert cpiResponse != null;
        System.out.println("cpiResponse: " + cpiResponse.getResults());
        ResponseEntity<String> response = restTemplate.getForEntity(cpiApiUrl, String.class);
        System.out.println("response: " + response);

        //System.out.println("response: " + response);
        String body = response.getBody();
        //JSONObject jsonBody = new JSONObject(body);
        MediaType contentType = response.getHeaders().getContentType();
        //System.out.println(contentType);
        HttpStatusCode statusCode = response.getStatusCode();
        //System.out.println(statusCode);
        return body;
    }
}

class Root {
    private Results Results;

    public Results getResults() {
        return Results;
    }

    public void setResults(Results Results) {
        this.Results = Results;
    }
}

class Results {
    private List<Series> series;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}

class Series {
    private String seriesID;
    private List<Data> data;

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

class Data {
    private String year;
    private String period;
    private String periodName;
    private String value;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
