package com.raktar.raktarapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    // ... a start metódus változatlan marad ...

    public static void main(String[] args) {
        // Minden teszt kódnak ide, a kapcsos zárójelek közé kell kerülnie! 📥

        List<AbstractProduct> inventory = new ArrayList<>();
        inventory.add(new PerishableProduct("Tej", "T001", 50, 450.0, "2026-02-15"));
        inventory.add(new DurableProduct("Fúró", "G042", 10, 15000.0, 24));

        System.out.println("--- Raktárkészlet ---");

        for (AbstractProduct p : inventory) {
            System.out.println(p.getProductDetails());

            if (p instanceof Discountable) {
                ((Discountable) p).applyDiscount(10);
                System.out.println("-> Ez a termék kapott 10% kedvezményt!");
                System.out.println("-> Új részletek: " + p.getProductDetails());
            }
            System.out.println("--------------------");
        }

        // Ez a sor indítja el a JavaFX ablakot a teszt után
        launch();
    }
}
