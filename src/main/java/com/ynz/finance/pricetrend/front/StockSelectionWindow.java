package com.ynz.finance.pricetrend.front;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import com.ynz.finance.pricetrend.helpers.LoadNasdaqStocks;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.jfree.chart.fx.ChartViewer;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;


@Component
@NoArgsConstructor
public class StockSelectionWindow extends Application {


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
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //split pane: right pane
        //top Node: a TabPane
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("price-volume", createRightBox());
        tabPane.getTabs().addAll(tab1);

        splitPane.getItems().addAll(scrollPane, tabPane);
        splitPane.setDividerPositions(0.25);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenHeight = screenSize.getHeight();
        double screenWidth = screenSize.getWidth();

        Scene scene = new Scene(splitPane, screenWidth * 0.7, screenHeight * 0.7);

        return scene;
    }

    protected TreeTableView<NasdaqStock> createTreeTableView() {
        TreeTableView<NasdaqStock> treeTableView = new TreeTableView<>();
        TreeTableColumn<NasdaqStock, String> columnTicker = new TreeTableColumn<>("Symbol");
        columnTicker.setPrefWidth(150);
        TreeTableColumn<NasdaqStock, String> columnSecurity = new TreeTableColumn<>("Security Name");
        columnSecurity.setPrefWidth(500);
        columnSecurity.setResizable(true);
        TreeTableColumn<NasdaqStock, String> columnMarketCategory = new TreeTableColumn<>("Market Category");
        TreeTableColumn<NasdaqStock, String> columnETF = new TreeTableColumn<>("ETF");

        columnTicker.setCellValueFactory(new TreeItemPropertyValueFactory<>("symbol"));
        columnSecurity.setCellValueFactory(new TreeItemPropertyValueFactory<>("securityName"));
        columnMarketCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("marketCategory"));
        columnETF.setCellValueFactory(new TreeItemPropertyValueFactory<>("eTF"));

        treeTableView.getColumns().addAll(Arrays.asList(columnTicker, columnSecurity, columnMarketCategory, columnETF));

        TreeItem<NasdaqStock> rootTreeItem = prepareTreeTableData(new LoadNasdaqStocks().groupBySymbol());
        treeTableView.setRoot(rootTreeItem);

        treeTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("newValue is instance of treeItem? " + (newValue instanceof TreeItem));
                    System.out.println("Selected Text : " + newValue.getValue().getSymbol());
                }
        );

        return treeTableView;
    }

    private TreeItem<NasdaqStock> prepareTreeTableData(Map<Character, TreeSet<NasdaqStock>> characterTreeSetMap) {

        NasdaqStock root = NasdaqStock.builder().symbol("Stocks").eTF("").financialStatus("").marketCategory("")
                .nextShares("").roundLotSize("").securityName("").testIssue("").build();

        //root item
        TreeItem<NasdaqStock> rootItem = new TreeItem<>(root);
        rootItem.setExpanded(true);

        //for each initial (A->Z)
        for (Character initial : characterTreeSetMap.keySet()) {
            NasdaqStock initialItem = NasdaqStock.builder().symbol(initial.toString()).eTF("").financialStatus("")
                    .marketCategory("").nextShares("").roundLotSize("").securityName("").testIssue("").build();

            TreeItem<NasdaqStock> initialTreeItem = new TreeItem<>(initialItem);
            rootItem.getChildren().add(initialTreeItem);

            //for each stocks having this initial
            TreeSet<NasdaqStock> stocks = characterTreeSetMap.get(initial);
            for (NasdaqStock s : stocks) {
                TreeItem<NasdaqStock> stockTreeItem = new TreeItem<>(s);
                initialTreeItem.getChildren().add(stockTreeItem);
            }
        }

        return rootItem;
    }

    protected VBox createRightBox() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        ChartViewer pvPlots = createPriceVolumeChartViewer("price-volume plots");
        pvPlots.setPrefHeight(600);

        vBox.getChildren().add(pvPlots);
        vBox.getChildren().add(new Label("it will hold a table here"));

        return vBox;
    }

    protected ChartViewer createPriceVolumeChartViewer(String title) {
        PriceVolumeCandleChart candleChart = new PriceVolumeCandleChart("");

        ChartViewer viewer = new ChartViewer(candleChart.createPriceVolumeCombinedChart(title), true);

        return viewer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
