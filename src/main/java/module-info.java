module chubrick {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires static com.fasterxml.jackson.databind;


    opens ru.mmm to javafx.fxml;
    exports ru.mmm;
}