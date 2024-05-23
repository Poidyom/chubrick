package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Setter;

/** Класс - контроллер сцены настроек. */
public class SettingsController {
    /** Указатель на приложение. */
    @Setter
    private App app;

    /** Контейнер с изображением. */
    @FXML
    private ImageView chubrImage;

    /**
     * Установить изображение чубрика.
     * @param imageUrl путь к файлу с изображением
     */
    public void setChubrImage(String imageUrl) {
        chubrImage.setImage(new Image(getClass().getResource(imageUrl).toExternalForm()));
    }

    /** Вернуться в главное окно. */
    @FXML
    private void handleBackToMain() {
        app.showMainView();
    }

    /** Поменять цвет чубрика. */
    @FXML
    private void handleChangeColor() { //TODO: будут правки
        if (Chubrick.getColor() == 1) {
            Chubrick.setColor(2);
        } else {
            Chubrick.setColor(1);
        }

        setChubrImage(Chubrick.GetPathToAppearance());
    }

    /** Поменять вид чубрика. */
    @FXML
    private void handleChangeForm() { //TODO: будут правки
        if (Chubrick.getFormName() == "hare") {
            Chubrick.setFormName("angel");
        } else if (Chubrick.getFormName() == "angel") {
            Chubrick.setFormName("clown");
        } else {
            Chubrick.setFormName("hare");
        }

        setChubrImage(Chubrick.GetPathToAppearance());
    }
}
