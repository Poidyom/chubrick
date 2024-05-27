package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Getter;

/** Класс - контроллер элемента списка. */
@Getter
public class ItemController {
    /** Текст задания. */
    @FXML
    private Label taskName;

    /** Кнопка удалить задание. */
    @FXML
    private Button deleteButton;

    /** Кнопка отметить выполненным. */
    @FXML
    private Button checkButton;
}
