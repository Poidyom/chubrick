package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import lombok.Setter;

public class MainController {

    @FXML
    private Button todoButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Rectangle healthBar;

    @FXML
    private Rectangle foodBar;

    @FXML
    private Rectangle happyBar;

    @Setter
    private App app;

    @FXML
    public void initialize() {
        todoButton.setOnAction(event -> {
            if (app != null) {
                app.openTODO();
            }
        });

        foodButton.setOnAction(event -> {
            if (app != null) {
                app.feed();
            }
        });

        settingsButton.setOnAction(event -> {
            if (app != null) {
                app.openSettings();
            }
        });
    }

    public void setBars(double healthLength, double foodLength, double happyLength) {
        healthBar.setWidth(healthLength);
        foodBar.setWidth(foodLength);
        happyBar.setWidth(happyLength);
    }


}

