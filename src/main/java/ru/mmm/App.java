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
    //int health = 150;
    //int food = 150;
    //int happy = 150;

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

        Chubrick.SetCharacterParams(150, 150, 150);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {

                    // Тут будет вызов тика на чубрике
                    // и обновление даных
                    Chubrick.MinusHealth(10); //если вернул true, то чел умер
                    Chubrick.MinusEat(4);
                    Chubrick.MinusBore(5);

                    // Обновление данных
                    controller.setBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getBoredom());
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
        Chubrick.PlusEat(4);
        controller.setBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getBoredom());
    }

    public void openSettings() {
        System.out.println("Будут настройки");
    }

    public void openTODO() {
        System.out.println("Будет туду");
    }
}