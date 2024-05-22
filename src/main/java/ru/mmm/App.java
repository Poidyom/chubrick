package ru.mmm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.WindowEvent;

/** Класс - точка входа в приложение. */
public class App extends Application {
    /** Главное окно. */
    private Stage primaryStage;

    /** Главная сцена. */
    private Scene mainScene;

    /** Сцена настроек. */
    private Scene settingsScene;

    /** Контроллер главной сцены. */
    private MainController mainController;

    /** Контроллер сцены настроек. */
    private SettingsController settingsController;

    /** Старт приложения.
     * @param primaryStage - главное окно. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // Загрузка основного окна
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent mainRoot = mainLoader.load();
        mainController = mainLoader.getController();
        mainController.setApp(this);
        mainScene = new Scene(mainRoot);

        // Загрузка окна настроек
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
        Parent settingsRoot = settingsLoader.load();

        settingsController = settingsLoader.getController();
        settingsController.setApp(this);
        settingsScene = new Scene(settingsRoot);

        // Инициализация параметров чубрика

        // TODO: Тут должен быть вызов метода чубрика, который все загружает
        Chubrick.SetCharacterParams(150, 150, 150, 0);

        // Таймлайн для еды
        Timeline timelineFood = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (Chubrick.MinusHealth(10)) {
                        changeStateToDeath(); //TODO: обработка смерти
                    }
                    Chubrick.MinusEat(4);
                    mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
                })
        );

        // Таймлайн для скуки
        Timeline timelineBore = new Timeline(
                new KeyFrame(Duration.seconds(60), event -> {
                    Chubrick.MinusHappy(5);
                    mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
                })
        );

        timelineBore.setCycleCount(Timeline.INDEFINITE);
        timelineBore.play();

        timelineFood.setCycleCount(Timeline.INDEFINITE);
        timelineFood.play();

        // Установка основной сцены и отображение
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Чубрик");
        primaryStage.show();


        // Установка обработки закрытия
        primaryStage.setOnCloseRequest(event -> handleExit(event));
    }

    /** Поменять состояние при смерти. */
    private void changeStateToDeath() {
        //TODO: тут меняется картинка на гробик наверное.
    }

    public static void main(String[] args) {
        launch(args); //todo!!!!! убрать? ХЗ зачем оно нужно
    }

    /** Покормить чубрика. */
    public void feed() {
        System.out.println("Чубрик кушает");
        Chubrick.PlusEat(4);
        mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
    }

    /** Открыть сцену TO-DO списка. */
    public void showToDoView() {
        System.out.println("Будет туду"); //TODO: сделать сцену
//        primaryStage.setScene(todoScene);
    }

    /** Открыть сцену настроек. */
    public void showSettingsView() {
        primaryStage.setScene(settingsScene);
    }

    /** Открыть главную сцену. */
    public void showMainView() {
        primaryStage.setScene(mainScene);
    }

    /** Смена изображения чубрика */
    public void changeChubrImage() {
        mainController.setChubrImage("/ru/mmm/chubr_2_1.png"); //TODO: тут нужно передавать путь на файл с данных чубрика
    }

    /** Обработка выхода приложения.
     * @param event - событие окна. */
    private void handleExit(WindowEvent event) {
        // TODO: вызов метода сохраения статы чубрика
        System.out.println("Чубрик отключился");
    }
}