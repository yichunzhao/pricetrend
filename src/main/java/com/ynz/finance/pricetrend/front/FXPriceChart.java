package com.ynz.finance.pricetrend.front;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@NoArgsConstructor
public class FXPriceChart extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(createStockScene());
        stage.setTitle("Stock Selection");
        stage.show();
    }

    public Scene createStockScene() {
        //split pane: default orientation is horizontal
        SplitPane splitPane = new SplitPane();

        //left scroll pane
        ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setContent(new Label("Nasdaq Stocks"));
        scrollPane.setContent(createTreeTableView());

        //right pane
        VBox rightBox = new VBox();
        rightBox.getChildren().add(new Label("right vertical box"));

        splitPane.getItems().addAll(scrollPane, rightBox);
        //splitPane.setDividerPositions(0.2);

        Scene scene = new Scene(splitPane, 1600, 800);
        return scene;
    }

    protected TreeTableView<NasdaqStock> createTreeTableView() {
        TreeTableView<NasdaqStock> treeTableView = new TreeTableView<>();
        TreeTableColumn<NasdaqStock, String> columnTicker = new TreeTableColumn<>("symbol");
        TreeTableColumn<NasdaqStock, String> columnSecurity = new TreeTableColumn<>("securityName");
        TreeTableColumn<NasdaqStock, String> columnMarketCategory = new TreeTableColumn<>("marketCategory");
        TreeTableColumn<NasdaqStock, String> columnETF = new TreeTableColumn<>("etf");

        columnTicker.setCellValueFactory(new TreeItemPropertyValueFactory<>("brand"));
        columnSecurity.setCellValueFactory(new TreeItemPropertyValueFactory<>("security"));
        columnMarketCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("marketCategory"));
        columnETF.setCellValueFactory(new TreeItemPropertyValueFactory<>("eTF"));

        treeTableView.getColumns().addAll(Arrays.asList(columnTicker, columnSecurity, columnMarketCategory, columnETF));

        return treeTableView;
    }

    private List<TreeItem<TreeItem>> prepareTreeTableViewData() {
        List<TreeItem<TreeItem>> treeItems = new ArrayList<>();


        return treeItems;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
