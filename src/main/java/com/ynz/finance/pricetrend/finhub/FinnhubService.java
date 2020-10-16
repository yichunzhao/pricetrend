package com.ynz.finance.pricetrend.finhub;

import com.ynz.finance.pricetrend.domain.Symbol;
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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class FinnhubService implements Finnhub {
    private final RestTemplate restTemplate;

    @Value("${uri.us.symbols}")
    private String USStockSymbolsURL;

    @Value("${api-key}")
    private String apiKey;

    @Value("${header.tokenKey}")
    private String tokenHeaderKey;

    public FinnhubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockSymbol> getAllUSStockSymbol() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(tokenHeaderKey, apiKey);
        ResponseEntity<StockSymbol[]> response = restTemplate.exchange(USStockSymbolsURL, HttpMethod.GET, new HttpEntity<>(headers), StockSymbol[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Map<Symbol, StockSymbol> getUSSymbolStockMap() {
        List<StockSymbol> stockSymbols = getAllUSStockSymbol();
        return stockSymbols.stream().collect(Collectors.toMap(s -> Symbol.of(s.getSymbol().trim()), s -> s, (o, n) -> n, TreeMap::new));
    }
}
