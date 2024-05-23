package ru.mmm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListController{
    /** Указатель на приложение. */
    @Setter
    private App app;

    @FXML
    private VBox itemHolder;

    private final List<String> tasks = List.of( //TODO убрать "это пример"
            "Task 1: Текст задачи, текст задачи,Текст задачи, текст задачи, текстТекст задачи, текст задачи, текстТекст задачи, текст задачи, текст текст задачи, текст задачи, текст задачи, текст задачи, текст задачи,",
            "Task 2: Fix bugs",
            "Task 3: Update documentation",
            "Task 4: Prepare presentation",
            "Task 5: Team meeting",
            "Task 1: Текст задачи, текст задачи,Текст задачи, текст задачи, текстТекст задачи, текст задачи, текстТекст задачи, текст задачи, текст текст задачи, текст задачи, текст задачи, текст задачи, текст задачи,",
            "Task 2: Fix bugs",
            "Task 3: Update documentation",
            "Task 4: Prepare presentation"
    );

    public void load() { //TODO
        for (String task : tasks) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("item.fxml"));
                Node node = loader.load();
                ItemController itemController = loader.getController();

                itemController.getTaskName().setText(task);

                itemController.getDeleteButton().setOnAction(event -> itemHolder.getChildren().remove(node));

                itemHolder.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void addTask()
    {
        System.out.println("Добавится"); //TODO открыть окно с
    }
}