package org.sersoft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.sersoft.services.ControllerManager;

public class MainController implements Initializable {

    ControllerManager controllerManager;
//    @FXML
//    private void switchToSecondary() throws IOException {
//        App.setRoot("secondary");
//    }
private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       // App.setRoot("secondary");
    }


    public void showPrimary() {
        primaryStage.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    public void goNext(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sersoft/fxml/secondary.fxml"));
        Parent secondary_page_parent = loader.load();
        Scene secondary_page_scene = new Scene(secondary_page_parent);
        Stage app_stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();



        controllerManager = ControllerManager.getInstance();


        secondary_page_scene.getStylesheets().add("/styles/myDialogs.css");
        secondary_page_scene.getStylesheets().add("/styles/MainStyles.css");

        app_stage.hide(); //optional
        app_stage.setTitle("Calcularea Algoritmilor");
        app_stage.setResizable(true);
        app_stage.setScene(secondary_page_scene);
        app_stage.show();

        AlgoritmController controller = loader.getController();
        controller.setPrimaryStage(app_stage);
        controller.showPrimary();

        ControllerManager.setAlgoritmController(controller);



    }

}
