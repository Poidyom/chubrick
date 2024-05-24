package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Setter;

import java.util.Objects;

public class TaskController {
    /** Указатель на приложение. */
    @Setter
    private App app;

    @FXML
    TextField textInput;

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