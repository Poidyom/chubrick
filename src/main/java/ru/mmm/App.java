package ru.mmm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Класс - точка входа в приложение. */
public class App extends Application {

    double health = 150;
    double food = 150;
    double happy = 150;

    MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загрузка FXML-файла
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();

        // инициализация
        controller = loader.getController();
        controller.setApp(this);

        // TODO: тут нужно подгружать статы чубрика

        controller.setBars(health, food, happy);


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {

                    // TODO: обновление health, food, happy, уже от самого чубрика
                    // Тут будет вызов тика на чубрике
                    // и обновление даных
                    health--;
                    food -= 4;
                    happy -= 5;

                    // Обновление данных
                    controller.setBars(health, food, happy);
                })
        );

        // Непрерывное выполнение анимации
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Создание сцены и установка корневого узла
        Scene scene = new Scene(root);

        // Установка сцены для primaryStage и отображение его
        primaryStage.setScene(scene);
        primaryStage.setTitle("Чубрик");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void feed() {
        System.out.println("Чубрик кушает");
        food += 2;
        controller.setBars(health, food, happy);
    }

    public void openSettings() {
        System.out.println("Будут настройки");
    }

    public void openTODO() {
        System.out.println("Будет туду");
    }
}