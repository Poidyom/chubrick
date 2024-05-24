package ru.mmm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Setter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
        int c = 0;

        if (ToDoList.getTasks() == null) {
            return;
        }

        itemHolder.getChildren().clear();

        for (String task : ToDoList.getTasks()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("item.fxml"));
                Node node = loader.load();
                ItemController itemController = loader.getController();

                itemController.getTaskName().setText(task);

                node.setId(String.valueOf(c++));

                itemController.getDeleteButton().setOnAction(event -> {
                    itemHolder.getChildren().remove(node);
                    ToDoList.DeleteTask(Integer.parseInt(node.getId()));
                    load();
                });

                itemController.getCheckButton().setOnAction(event -> {
                    System.out.println(itemController.getTaskName().getText());
                    ToDoList.markAsCompleted(Integer.parseInt(node.getId()));
                    load();
                });

                itemHolder.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private int count = 0;
    @FXML
    public void addTask() {
        System.out.println("Добавится");
        ToDoList.AddTask("Test task №" + String.valueOf(count++));
        load();
    }

    @FXML
    public void exitToMain() {
        app.showMainView();
    }
}