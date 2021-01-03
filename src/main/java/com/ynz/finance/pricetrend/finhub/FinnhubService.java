package com.ynz.finance.pricetrend.finhub;

import com.ynz.finance.pricetrend.domain.StockSymbol;
import com.ynz.finance.pricetrend.domain.Symbol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
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

    @Value("${uri.us.symbols.nasdaq.xncm}")
    private String usNasdaqCapitalURL;

    private MultiValueMap<String, String> headers = new HttpHeaders();

    @PostConstruct
    private void init() {
        headers.add(tokenHeaderKey, apiKey);
    }

    public FinnhubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockSymbol> getAllUSStockSymbol() {
        ResponseEntity<StockSymbol[]> response = restTemplate.exchange(USStockSymbolsURL, HttpMethod.GET, new HttpEntity<>(headers), StockSymbol[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public Map<Symbol, StockSymbol> getUSSymbolStockMap() {
        List<StockSymbol> stockSymbols = getAllUSStockSymbol();
        return stockSymbols.stream().collect(Collectors.toMap(s -> Symbol.of(s.getSymbol().trim()), s -> s, (o, n) -> n, TreeMap::new));
    }

    @Override
    public Map<Symbol, StockSymbol> getAllNasdaqStocks() {
        ResponseEntity<StockSymbol[]> response = restTemplate.exchange(usNasdaqCapitalURL, HttpMethod.GET, new HttpEntity<>(headers), StockSymbol[].class);
        String allStocks = Arrays.asList(response.getBody()).stream().map(ss -> ss.toString()).collect(Collectors.joining("\n"));
        writeToFile(allStocks, "us-nasdaq-stocks.text");

        return Arrays.asList(response.getBody()).stream().collect(Collectors.toMap(ss -> Symbol.of(ss.getSymbol()), ss -> ss, (ss1, ss2) -> ss2, TreeMap::new));
    }

    private void writeToFile(String data, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName + ".text");
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Success--.");
    }
}
