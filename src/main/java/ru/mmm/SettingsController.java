package ru.mmm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;

/** Класс - контроллер сцены настроек. */
public class SettingsController {
    /** Указатель на приложение. */
    @Setter
    private App app;

    /** Вернуться в главное окно. */
    @FXML
    private void handleBackToMain() {
        app.showMainView();
    }

    /** Поменять цвет. */
    @FXML
    private void handleChangeColor() {
        app.changeChubrImage(); //TODO: тут вызов метода смены цвета будет
    }

//    /** Поменять тип. */
//    @FXML
//    private void changeType() {
//        app.changeTypeForChubr();
//    }
}
