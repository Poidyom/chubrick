package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Getter;


@Getter
public class ItemController {
    @FXML
    private Label taskName;

    @FXML
    private Button deleteButton;

    @FXML
    private Button checkButton;
}
