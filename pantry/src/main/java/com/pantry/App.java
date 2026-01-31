package com.pantry;

import com.pantry.config.DatabaseConfig;
import com.pantry.model.UserItem;
import com.pantry.repository.SQLiteInventoryRepository;
import com.pantry.service.InventoryService;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class App extends Application {

    private InventoryService inventoryService;
    private TableView<UserItem> table;

    @Override
    public void start(Stage stage) {
        // Initialize backend
        DatabaseConfig.initializeDatabase();
        inventoryService = new InventoryService(new SQLiteInventoryRepository());

        // Root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Table
        table = createTable();
        root.setCenter(table);

        // Controls
        HBox controls = createControls();
        root.setBottom(controls);

        // Load data
        refreshTable();

        // Stage setup
        Scene scene = new Scene(root, 700, 450);
        stage.setTitle("Pantry Tracker");
        stage.setScene(scene);
        stage.show();
    }

    private TableView<UserItem> createTable() {
        TableView<UserItem> tableView = new TableView<>();

        TableColumn<UserItem, String> nameCol = new TableColumn<>("Item");
        nameCol.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleStringProperty(data.getValue().getName())
        );
        nameCol.setPrefWidth(250);

        TableColumn<UserItem, Number> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity())
        );
        qtyCol.setPrefWidth(100);

        TableColumn<UserItem, String> expCol = new TableColumn<>("Expiration Date");
        expCol.setCellValueFactory(data -> 
            new javafx.beans.property.SimpleStringProperty(
                data.getValue().getExpirationDate().toString()
            )
        );
        expCol.setPrefWidth(200);

        tableView.getColumns().addAll(nameCol, qtyCol, expCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    private HBox createControls() {
        Button addButton = new Button("Add Item");
        Button removeButton = new Button("Remove Selected");
        Button refreshButton = new Button("Refresh");

        addButton.setOnAction(e -> handleAddItem());
        removeButton.setOnAction(e -> handleRemoveItem());
        refreshButton.setOnAction(e -> refreshTable());

        HBox box = new HBox(10, addButton, removeButton, refreshButton);
        box.setPadding(new Insets(10));

        return box;
    }

    private void refreshTable() {
        ObservableList<UserItem> items =
                FXCollections.observableArrayList(inventoryService.getAllItems());
        table.setItems(items);
    }

    private void handleAddItem() {
        // TEMP: hard-coded item (you'll replace this with a dialog)
        UserItem item = new UserItem(
                "Sample Item",
                LocalDate.now().plusDays(7),
                1
        );

        inventoryService.addItem(item);
        refreshTable();
    }

    private void handleRemoveItem() {
        UserItem selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("No Selection", "Please select an item to remove.");
            return;
        }

        inventoryService.removeItemById(selected.getId());
        refreshTable();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
