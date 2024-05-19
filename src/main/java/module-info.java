module chubrick {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ru.mmm to javafx.fxml;
    exports ru.mmm;
}