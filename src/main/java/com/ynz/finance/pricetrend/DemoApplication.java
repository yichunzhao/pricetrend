package com.ynz.finance.pricetrend;

import com.ynz.finance.pricetrend.front.FXPriceChart;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class DemoApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    private FXPriceChart fxPriceChart;

    @Override
    public void init() throws Exception {
        log.info("Application.init() is invoked");
        applicationContext = SpringApplication.run(DemoApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Application.start() is invoked");
        Label label = new Label("hello Spring boot");

        fxPriceChart = applicationContext.getBean(FXPriceChart.class);

        //stage.setScene(new Scene(label));
        stage.setScene(fxPriceChart.createStockScene());
        stage.setTitle("Run FX from SpringBoot");
        stage.show();
    }

    @Autowired
    public void setFxPriceChart(FXPriceChart fxPriceChart) {
        this.fxPriceChart = fxPriceChart;
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
