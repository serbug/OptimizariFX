module org.sersoft {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.sersoft to javafx.fxml;
    exports org.sersoft;

     opens org.sersoft.controller to javafx.fxml;
    exports org.sersoft.controller;


}