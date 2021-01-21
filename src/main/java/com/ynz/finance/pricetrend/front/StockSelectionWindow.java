package com.ynz.finance.pricetrend.front;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import com.ynz.finance.pricetrend.helpers.LoadNasdaqStocks;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.jfree.chart.fx.ChartViewer;
import org.springframework.stereotype.Component;

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

        //right pane
        //GridPane rightPane = createRightPane();
        ChartViewer viewer = createPriceVolumeChartViewer("");
        viewer.setManaged(true);

        Pane pane = new Pane();
        pane.getChildren().add(viewer);



        splitPane.getItems().addAll(scrollPane, viewer);
        splitPane.setDividerPositions(0.25);

        Scene scene = new Scene(splitPane, 1600, 800);
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

    protected GridPane createRightPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        ChartViewer pvPlots = createPriceVolumeChartViewer("");

        gridPane.add(pvPlots, 0, 0, 1, 1);
        gridPane.add(new Label("hold a table here"), 0, 1, 1, 1);

        return gridPane;
    }

    protected VBox createRightBox() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone", 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet", 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        ChartViewer pvPlots = createPriceVolumeChartViewer("");
        vBox.getChildren().add(pieChart);
        vBox.getChildren().add(pvPlots);

        vBox.getChildren().add(new Label("hold a table here"));

        return vBox;
    }

    protected ChartViewer createPriceVolumeChartViewer(String title) {
        PriceVolumeCandleChart candleChart = new PriceVolumeCandleChart("");

        ChartViewer viewer = new ChartViewer(candleChart.createPriceVolumeCombinedChart("price-volume"),true);

        return viewer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
