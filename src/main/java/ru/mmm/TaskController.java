package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.util.Objects;

/** Класс - контроллер элемента списка заданий. */
public class TaskController {
    /** Указатель на приложение. */
    @Setter
    private App app;

    /** Поле ввода текста. */
    @FXML
    TextField textInput;

    /** Добавить новое задание. */
    @FXML
    public void addTask() {
        String input = textInput.getText();
        if (!Objects.equals(input, ""))
        {
            ToDoList.AddTask(input);
            app.showToDoView();
        }
    }
}