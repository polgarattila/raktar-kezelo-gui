package com.raktar.raktarapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Optional;

public class HelloController {

    // FXML elemek összekötése
    @FXML private TableView<AbstractProduct> productTable;
    @FXML private TableColumn<AbstractProduct, String> nameColumn;
    @FXML private TableColumn<AbstractProduct, String> skuColumn;
    @FXML private TableColumn<AbstractProduct, Integer> quantityColumn;
    @FXML private TableColumn<AbstractProduct, Double> priceColumn;
    @FXML private TableColumn<AbstractProduct, String> detailsColumn;

    @FXML private TextField nameField;
    @FXML private TextField skuField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private ComboBox<String> typeComboBox;

    @FXML private Label expiryLabel;
    @FXML private DatePicker expiryDatePicker;
    @FXML private Label warrantyLabel;
    @FXML private TextField warrantyField;

    @FXML private Label welcomeText;

    // A termékek listája
    private ObservableList<AbstractProduct> inventory = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Táblázat oszlopainak beállítása
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        skuColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("productDetails"));

        // ComboBox feltöltése
        typeComboBox.setItems(FXCollections.observableArrayList("Romlandó", "Tartós"));
        typeComboBox.getSelectionModel().selectFirst();

        // Típusváltás figyelése (Láthatóság kezelése) 🕵️‍♂️
        typeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateFieldsVisibility(newVal);
        });

        updateFieldsVisibility(typeComboBox.getValue());
        productTable.setItems(inventory);

        // Példa egy romlandó termékre:
        inventory.add(new PerishableProduct("Tej", "TEJ001", 10, 450.0, "2024-06-20"));

        // Példa egy tartós termékre:
        inventory.add(new DurableProduct("Kalapács", "KAL001", 5, 25000.0, 24));
    }

    @FXML
    private void onSaveButtonClick() {
        try {
            // Adatok beolvasása
            String name = nameField.getText();
            String sku = skuField.getText();

            if (name.isBlank() || sku.isBlank()) {
                welcomeText.setText("❌ Hiba: Név és Cikkszám kötelező!");
                return;
            }

            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            if (quantity <= 0 || price <= 0) {
                welcomeText.setText("❌ Hiba: Pozitív számokat adj meg!");
                return;
            }

            String selectedType = typeComboBox.getValue();
            AbstractProduct newProduct;

            // Példányosítás típus alapján
            if ("Romlandó".equals(selectedType)) {
                if (expiryDatePicker.getValue() == null) {
                    welcomeText.setText("❌ Hiba: Válassz lejárati dátumot!");
                    return;
                }
                newProduct = new PerishableProduct(name, sku, quantity, price, expiryDatePicker.getValue().toString());
            } else {
                int warranty = Integer.parseInt(warrantyField.getText());
                if (warranty < 0) {
                    welcomeText.setText("❌ Hiba: A garancia nem lehet negatív!");
                    return;
                }
                newProduct = new DurableProduct(name, sku, quantity, price, warranty);
            }

            inventory.add(newProduct);
            clearFields();
            welcomeText.setText("✅ Sikeres mentés: " + name);

        } catch (NumberFormatException e) {
            welcomeText.setText("❌ Hiba: Hibás számformátum!");
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        AbstractProduct selected = productTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            // Megerősítő ablak 🛡️
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Törlés megerősítése");
            alert.setHeaderText("Biztosan törölni akarod?");
            alert.setContentText(selected.getName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                inventory.remove(selected);
                welcomeText.setText("🗑️ Törölve: " + selected.getName());
            }
        } else {
            welcomeText.setText("⚠️ Válassz ki valamit a törléshez!");
        }
    }

    @FXML
    private void onDiscountButtonClick() {
        AbstractProduct selected = productTable.getSelectionModel().getSelectedItem();
        if (selected instanceof Discountable d) {
            d.applyDiscount(10);
            productTable.refresh();
            welcomeText.setText("💰 10% kedvezmény: " + selected.getName());
        } else if (selected != null) {
            welcomeText.setText("🚫 Ez a termék nem leárazható!");
        }
    }

    private void updateFieldsVisibility(String type) {
        boolean isPerishable = "Romlandó".equals(type);

        expiryLabel.setVisible(isPerishable);
        expiryLabel.setManaged(isPerishable);
        expiryDatePicker.setVisible(isPerishable);
        expiryDatePicker.setManaged(isPerishable);

        warrantyLabel.setVisible(!isPerishable);
        warrantyLabel.setManaged(!isPerishable);
        warrantyField.setVisible(!isPerishable);
        warrantyField.setManaged(!isPerishable);
    }

    private void clearFields() {
        nameField.clear();
        skuField.clear();
        quantityField.clear();
        priceField.clear();
        warrantyField.clear();
        expiryDatePicker.setValue(null);
        typeComboBox.getSelectionModel().selectFirst();
    }
}