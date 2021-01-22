package com.ynz.finance.pricetrend;

import com.ynz.finance.pricetrend.front.StockSelectionWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class DemoApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    private StockSelectionWindow fxPriceChart;

    @Override
    public void init() throws Exception {
        log.info("Application.init() is invoked");

        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        builder.headless(false);
        applicationContext = builder.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Application.start() is invoked");

        fxPriceChart = applicationContext.getBean(StockSelectionWindow.class);

        stage.setScene(fxPriceChart.createStockScene());
        stage.setTitle("Run FX from SpringBoot");
        stage.show();
    }

    public static void main(String[] args) {
        log.info("SpringBoot.main() is invoked");
        //launch(args);
        Application.launch(DemoApplication.class, args);
    }

    @Override
    public void stop() throws Exception {
        //corresponding init() method; we may nicely terminate them
        //terminate Spring app context
        applicationContext.stop();

        //terminate FX
        Platform.exit();
    }
}
