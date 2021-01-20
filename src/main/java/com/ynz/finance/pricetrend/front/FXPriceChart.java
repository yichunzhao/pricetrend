package com.ynz.finance.pricetrend.front;

import com.ynz.finance.pricetrend.domain.nasdaq.NasdaqStock;
import com.ynz.finance.pricetrend.helpers.LoadNasdaqStocks;
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

import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;


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

        columnTicker.setCellValueFactory(new TreeItemPropertyValueFactory<>("symbol"));
        columnSecurity.setCellValueFactory(new TreeItemPropertyValueFactory<>("securityName"));
        columnMarketCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("marketCategory"));
        columnETF.setCellValueFactory(new TreeItemPropertyValueFactory<>("eTF"));

        treeTableView.getColumns().addAll(Arrays.asList(columnTicker, columnSecurity, columnMarketCategory, columnETF));

        TreeItem<NasdaqStock> rootTreeItem = prepareTreeTableData(new LoadNasdaqStocks().groupBySymbol());
        treeTableView.setRoot(rootTreeItem);

        return treeTableView;
    }

    private TreeItem<NasdaqStock> prepareTreeTableData(Map<Character, TreeSet<NasdaqStock>> characterTreeSetMap) {

        NasdaqStock root = NasdaqStock.builder().symbol("Stocks").eTF("").financialStatus("").marketCategory("")
                .nextShares("").roundLotSize("").securityName("").testIssue("").build();

        //root item
        TreeItem<NasdaqStock> rootItem = new TreeItem<>(root);

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

    public static void main(String[] args) {
        launch(args);
    }
}
