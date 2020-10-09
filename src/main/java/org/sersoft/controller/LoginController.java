package org.sersoft.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.sersoft.services.ControllerManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    ControllerManager controllerManager;

    @FXML
    private Label label;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pswPassword;

    @FXML
    private Button btButtonSubmit;

    @FXML
    public static Button mute;
    @FXML
    private static Button plays;


    @FXML
    private AnchorPane anchorPaneLogin;
    @FXML
    private ImageView imgPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String text="W-E-L-C-O-M-E";
//       new Thread(() -> {
//
//
//     for (int i = 0; i < text.length(); i++) {
//         char c=text.charAt(i);
//
//         System.out.print(c+" ");
//
//         try {
//             Thread.sleep(1000);
//         } catch (InterruptedException ex) {
//             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//         }
//
//     }
// }).start();
        label.setText(text);


    }
    @FXML
    private void handlepswPassword(ActionEvent event) throws IOException {
        submit(event);
    }
    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {

        submit(event);

    }
    void submit(ActionEvent event) throws IOException{
        Reflection reflection = new Reflection();
        reflection.setFraction(1);
        reflection.setTopOffset(-2);

        label.setEffect(reflection);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sersoft/fxml/primary.fxml"));
        Parent home_page_parent = loader.load();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        if (isValidInput(event)) {

            if (txtUsername.getText().equals("user") && pswPassword.getText().equals("password")) {

                label.setText(" Autentificare cu succes!");

                controllerManager = ControllerManager.getInstance();


                home_page_scene.getStylesheets().add("/styles/myDialogs.css");
                home_page_scene.getStylesheets().add("/styles/MainStyles.css");

                app_stage.hide(); //optional
                app_stage.setTitle("LABORATOR MMC II");
                app_stage.setResizable(true);
                app_stage.setScene(home_page_scene);
                app_stage.show();

                MainController controller = loader.getController();
                controller.setPrimaryStage(app_stage);
                controller.showPrimary();

                ControllerManager.setPrimaryController(controller);


            } else {
                label.setText(" Autentificare esuata!");

                txtUsername.setText("");
                pswPassword.setText("");

            }

        }

    }


    private boolean isValidInput(ActionEvent event) {
        Boolean validInput = true;

        if (txtUsername == null || txtUsername.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Username is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if (emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                txtUsername.requestFocus();
            }
        }
        if (pswPassword == null || pswPassword.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Password is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if (emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                pswPassword.requestFocus();
            }
        }
        return validInput;
    }
//     @FXML
//   private void handleKyepress(ActionEvent event) throws IOException{
//
//
//
//
//     Reflection reflection = new Reflection();
//        reflection.setFraction(1);
//        reflection.setTopOffset(-2);
//
//        label.setEffect(reflection);
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
//        Parent home_page_parent = loader.load();
//        Scene home_page_scene = new Scene(home_page_parent);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//
//        if (isValidInput(event)) {
//
//            if (txtUsername.getText().equals("user") && pswPassword.getText().equals("password")) {
//
//                label.setText(" Autentificare cu succes!");
//
//                controllerManager = ControllerManager.getInstance();
//                PlayerMusic.playerMain();
//
//                home_page_scene.getStylesheets().add("/styles/myDialogs.css");
//                home_page_scene.getStylesheets().add("/styles/MainStyles.css");
//
//                app_stage.hide(); //optional
//
//                app_stage.setTitle("PASSWORD GENERATE");
//                app_stage.setResizable(true);
//                app_stage.setScene(home_page_scene);
//                app_stage.show();
//
//                MainController controller = loader.getController();
//                controller.setPrimaryStage(app_stage);
//                controller.showMain();
//
//                ControllerManager.setMainController(controller);
//
//            } else {
//                label.setText(" Autentificare esuata!");
//
//                txtUsername.setText("");
//                pswPassword.setText("");
//
//            }
//
//        }
//
//
//   }


}
