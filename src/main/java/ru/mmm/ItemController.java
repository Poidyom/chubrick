package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ItemController {
    @FXML
    private Label taskName;

    @FXML
    private Button deleteButton;

    public Label getTaskName() {
        return taskName;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
