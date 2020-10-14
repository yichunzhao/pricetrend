package com.ynz.finance.pricetrend.finhub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FinnhubService {
    private final RestTemplate restTemplate;

    @Value("${uri.us.symbols}")
    private String USStockSymbolsURL;

    @Value("${api-key}")
    private String apiKey;

    public FinnhubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockSymbol> getAllUSStockSymbol() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Finnhub-Token", apiKey);
        ResponseEntity<StockSymbol[]> response = restTemplate.exchange(USStockSymbolsURL, HttpMethod.GET, new HttpEntity<>(headers), StockSymbol[].class);
        return Arrays.asList(response.getBody());
    }

}
