package ru.mmm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.WindowEvent;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Класс - точка входа в приложение. */
public class App extends Application {
    /** Главное окно. */
    private Stage primaryStage;

    /** Главная сцена. */
    private Scene mainScene;

    /** Сцена настроек. */
    private Scene settingsScene;

    /** Сцена списка. */
    private Scene listScene;

    /** Сцена ввода задания. */
    private Scene taskScene;

    /** Контроллер главной сцены. */
    @Getter
    private MainController mainController;

    /** Контроллер сцены настроек. */
    private SettingsController settingsController;

    /** Контроллер сцены списка. */
    private ListController listController;

    /** Контроллер сцены ввода задания. */
    TaskController taskController;

    /** Старт приложения.
     * @param primaryStage главное окно. */
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

        // Загрузка окна списка
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource("list-view.fxml"));
        Parent listRoot = listLoader.load();
        listController = listLoader.getController();
        listController.setApp(this);
        listScene = new Scene(listRoot);

        // Загрузка окна ввода задания
        FXMLLoader taskLoader = new FXMLLoader(getClass().getResource("task-view.fxml"));
        Parent taskRoot = taskLoader.load();
        taskController = taskLoader.getController();
        taskController.setApp(this);
        taskScene = new Scene(taskRoot);

        // Инициализация параметров чубрика

        loadStats();

        Chubrick.SetCharacterParams(StateFile.getStartHealth(), StateFile.getStartHunger(), StateFile.getStartHappy(), StateFile.getStartAmountOfEaten());
        Chubrick.setFormName(StateFile.getStartFormName());
        Chubrick.setState(StateFile.getStartState());
        Chubrick.setColor(StateFile.getStartColor());
        Chubrick.setSp(StateFile.getSp());

        ToDoList.SetToDoList(StateFile.getStartToDoList());
        Quest.setQuestState(StateFile.getStartStateOfQuest());
        if (StateFile.getStartStateOfQuest().get(0) == 0)
        {
            Quest.creatingTaskOne();
        }

        mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());

        loadChubrImage();

        // Таймлайн для еды
        Timeline timelineFood = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    Chubrick.MinusHealth(10);
                    Chubrick.MinusEat(3);
                    mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
                    loadChubrImage();
                })
        );

        // Таймлайн для скуки
        Timeline timelineBore = new Timeline(
                new KeyFrame(Duration.seconds(30), event -> {
                    Chubrick.MinusHappy(10);
                    mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
                    loadChubrImage();
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
        loadChubrImage();

        primaryStage.getIcons().add(new Image(getClass().getResource("/ru/mmm/interface/icon.png").toExternalForm()));

        // Установка обработки закрытия
        primaryStage.setOnCloseRequest(this::handleExit);
    }

    /** Главный метод запуска приложения JavaFX.
     * @param args агументы командной строки, переданные в приложение.
     * */
    public static void main(String[] args) {
        launch(args);
    }

    /** Покормить чубрика. */
    public void feed() {
        if (Chubrick.PlusEat(30)) {
            System.out.println("Чубрик кушает");
        }
        Quest.handlerTaskOne(1);
        if (Quest.currentState() == 1)
        {
            mainController.showButton();
        }
        mainController.updateBars(Chubrick.getHealth(), Chubrick.getHunger(), Chubrick.getHappy());
    }

    /** Открыть сцену TO-DO списка. */
    public void showToDoView() {
        primaryStage.setScene(listScene);
        listController.load();
        Quest.handlerTaskOne(2);
    }

    /** Открыть сцену настроек. */
    public void showSettingsView() {
        primaryStage.setScene(settingsScene);
        loadChubrImage();
    }

    /** Открыть сцену ввода. */
    public void showTaskView() {
        taskController.textInput.setText("");
        primaryStage.setScene(taskScene);
    }

    /** Открыть главную сцену. */
    public void showMainView() {
        primaryStage.setScene(mainScene);
        if (Quest.currentState() == 1)
        {
            mainController.showButton();
        }
        loadChubrImage();
    }

    /**Завершение второго таска */
    public void pressInvisibleButton() {
        Quest.handlerTaskOfTwo();
        mainController.hideButton();
    }

    /** Загрузка изображения чубрика */
    public void loadChubrImage() {
        String path = Chubrick.GetPathToAppearance();
        if (primaryStage.getScene() == mainScene) {
            mainController.setChubrImage(path);
        } else if (primaryStage.getScene() == settingsScene) {
            settingsController.setChubrImage(path);
        }
    }

    /** Обработка выхода приложения.
     * @param event событие окна. */
    private void handleExit(WindowEvent event) {
        StateFile.convertToJson();
        System.out.println("Чубрик отключился");
    }

    /** Загрузка состояния чубрика. */
    private void loadStats() {

        File theDir = new File(System.getProperty("user.home") + "/Documents/MMM/chubrick");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        String filePath = System.getProperty("user.home") + "/Documents/MMM/chubrick/stateFile.json";
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("{\n" +
                        "  \"formNames\": [\n" +
                        "    \"hare\",\n" +
                        "    \"angel\",\n" +
                        "    \"clown\"\n" +
                        "  ],\n" +
                        "  \"colors\": [\n" +
                        "    1,\n" +
                        "    2\n" +
                        "  ],\n" +
                        "  \"states\": [\n" +
                        "    \"base\",\n" +
                        "    \"sad\",\n" +
                        "    \"death\"\n" +
                        "  ],\n" +
                        "  \"chubrick\": {\n" +
                        "    \"startHealth\": 150,\n" +
                        "    \"startHunger\": 150,\n" +
                        "    \"startHappy\": 150,\n" +
                        "    \"startAmountOfEaten\": 0,\n" +
                        "    \"startFormName\": \"hare\",\n" +
                        "    \"startColor\": 1,\n" +
                        "    \"startState\": \"base\",\n" +
                        "    \"sp\": \"\"\n" +
                        "  },\n" +
                        "  \"startToDoList\": [],\n" +
                        "  \"startStateOfQuest\" : [ 0, 0, 0 ]\n" +
                        "}");
                fileWriter.close();
            }
            StateFile.setStateFilePath(filePath);
            StateFile.loadFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}