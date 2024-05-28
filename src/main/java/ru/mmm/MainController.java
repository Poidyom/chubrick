package ru.mmm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.Setter;

/** Класс - контроллер главной сцены. */
public class MainController {
    /** Указатель на приложение. */
    @Setter
    private App app;

    /** Контейнер с изображением чубрика. */
    @FXML
    private ImageView chubrImage;

    /** Контейнер с изображением лампы. */
    @FXML
    private ImageView lampImage;

    /** Количество здоровья. */
    @FXML
    private Rectangle healthBar;

    /** Количество сытости. */
    @FXML
    private Rectangle foodBar;

    /** Количество счастья. */
    @FXML
    private Rectangle happyBar;

    /**
     * Показать текущую статистику чубрика.
     * @param health текущее значение здоровья
     * @param hunger текущее значение сытости
     * @param boredom текущее значение скуки
     */
    public void updateBars(int health, int hunger, int boredom) {
        healthBar.setWidth(health);
        foodBar.setWidth(hunger);
        happyBar.setWidth(boredom);
    }

    /**
     * Установить изображение чубрика.
     * @param imageUrl путь к файлу с изображением
     */
    public void setChubrImage(String imageUrl) {
        chubrImage.setImage(new Image(getClass().getResource(imageUrl).toExternalForm()));
    }

    /** Покормить чубрика*/
    @FXML
    private void handleFeed() {
        app.feed();
    }

    /** Открыть TO-DO список. */
    @FXML
    private void handleOpenTODO() {
        app.showToDoView();
    }

    /** Открыть настройки. */
    @FXML
    private void handleOpenSettings() {
        app.showSettingsView();
    }

    /** Выключить свет (сон). */
    @FXML
    private void handleLamp() {
        lampImage.setImage(new Image(getClass().getResource("/ru/mmm/interface/lamp_off.png").toExternalForm()));
        Timeline sleep = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    lampImage.setImage(new Image(getClass().getResource("/ru/mmm/interface/lamp_on.png").toExternalForm()));
                    Chubrick.Sleep();
                })
        );

        sleep.setCycleCount(1);
        sleep.play();
    }
}
