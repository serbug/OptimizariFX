package org.sersoft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MainApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sersoft/fxml/login.fxml"));

        //System.out.println(getClass().getResource("fxml/login.fxml"));
        Parent parent = loader.load();

        // plays.setVisible(false);


        Scene scene = new Scene(parent);


       scene.getStylesheets().add(String.valueOf(this.getClass().getResource("/org/sersoft/fxml/styles/login.css")));
        stage.getIcons().add(
                new Image(this.getClass().getResourceAsStream("/org/sersoft/fxml/image/utm-cover.jpg")));
       // stage.getIcons().add(new Image("/fxml/styles/iconapp.png"));
      //  stage.getIcons().add(new Image("/iconapp.png"));
        stage.setTitle("LOGIN");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.hide();
        stage.show();
     //   PlayerMusic.playerInto();
//      PlayerMusic.playerTest();

        //  playSound();
    }

//        scene = new Scene(loadFXML("primary"), 794 , 900);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    public static void main(String[] args) {
        launch(args);
    }

}