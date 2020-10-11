package org.sersoft.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sersoft.services.ControllerManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlgoritmController implements Initializable {


    public float a, b, ALFA;
    public float BETA = 1 / 2;
    public float DELTA = (float) 0.5;
    public float EPS = (float) Math.pow(10,-5);
    //X[0] si X[1]
    public float[] X = new float[2];
    //Variabila Z este o variabila intermediara a lui X(k+1)
    public float[] Z = new float[2];
    //Gradientul G[0] si G[1]
    public float[] G = new float[2];

    ControllerManager controllerManager;
    private Stage primaryStage;
    @FXML
    private Label labelTitleAlgoritm;
    @FXML
    private TextField textFieldValoareaA;
    @FXML
    private TextField textFieldValoareaB;
    @FXML
    private TextField textFieldPasulAlfa;
    @FXML
    private TextField textFieldCoordonataX;
    @FXML
    private TextField textFieldCoordonataY;
    @FXML
    private TextField textFieldFunctiaX;
    @FXML
    private TextField textFieldIteratii;
    @FXML
    private Button buttonGradientul;
    @FXML
    private Button buttonHestenes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controllerManager = ControllerManager.getInstance();

    }

    public void showPrimary() {
        primaryStage.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    public void handleButtonVarianta(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sersoft/fxml/variant.fxml"));
        Parent secondary_page_parent = loader.load();
        Scene secondary_page_scene = new Scene(secondary_page_parent);
        Stage app_stage = new Stage();

        secondary_page_scene.getStylesheets().add(String.valueOf(this.getClass().getResource("/org/sersoft/fxml/styles/variant.css")));

        app_stage.hide(); //optional
        app_stage.setTitle("Varianta 7");
        app_stage.setResizable(true);
        app_stage.setScene(secondary_page_scene);
        app_stage.show();

    }

    @FXML
    public void handleButtonGradientul(ActionEvent actionEvent) {


        //numarul de iteratii
        int iter = 0;
        float Ak;
        val_a_b();


        System.out.println("LOG: verificare valoarei alfa " + ALFA);

        X[0] = 1;
        X[1] = 1;


//       if(G[0]==0||G[0]<=EPS&&G[1]==0||G[1]<=EPS){
//           System.out.println("Solutia optimala la moment");
//       }else {


        calculatedGradient();

        // ||Gradient||^2
        double grad = Math.sqrt(Math.pow(G[0],2) + Math.pow(G[1],2));


            System.out.println("LOG: 1 valoarea functiei F(X) " + F(X[0], X[1]));

            while (grad >= EPS) {

                System.out.println("LOG: valoarea grad " + grad);
                ++iter;

                //antigradientul ca directie de deplasare
                Z[0] = X[0] - ALFA * G[0];
                Z[1] = X[1] - ALFA * G[1];

                //se verifica conditia pag.12 formula 1.11
                while ((F(Z[0], Z[1]) - F(X[0], X[1])) <= - DELTA * (ALFA * Math.pow(G[0], 2) + Math.pow(G[1], 2))) {
                    ALFA *= BETA;
                    Z[0] = X[0] - ALFA * G[0];
                    Z[1] = X[1] - ALFA * G[1];
                }
                System.out.println("LOG: 2 valoarea functiei F(X) " + F(X[0], X[1]));

                Ak = ALFA; //daca este indeplinita inegalitatea, atunci valoarea ALFA este acceptata si se va lua Ak=ALFA

                X[0] = X[0] - Ak * G[0];
                X[1] = X[1] - Ak * G[1];


                if (X[0] <= Z[0] && X[1] <= Z[1])
                    break;
                calculatedGradient();
            }
            System.out.println("LOG: 3 valoarea functiei F(X) " + F(X[0], X[1]));

            labelTitleAlgoritm.setText("Metoda Gradientului cu fractionarea pasului");

            textFieldCoordonataX.setText(String.valueOf(X[0]));
            textFieldCoordonataY.setText(String.valueOf(X[1]));
            textFieldFunctiaX.setText(String.valueOf(F(X[0], X[1])));
            textFieldIteratii.setText(String.valueOf(iter));





    }

    @FXML
    public void handleButtonHestenes(ActionEvent actionEvent) {


        int iter = 0, k = 0;
        float Ak;

        //matrice simetrica pozitiva
        float[] A = new float[2];

        //directia
        float[] d = new float[2];


        X[0] = 1;
        X[1] = 1;

        calculatedGradient();

        //daca gradientul = 0 atunci x* = x^(k+1) este o solutie optima.
        while ((G[0] != 0 && G[1] != 0)) {
            ++iter;

            if (k++ == 0) {

                d[0] = -G[0];
                d[1] = -G[1];

            } else {
                float v = A[0] * A[0] + A[1] * A[1];


                d[0] = -G[0] + (G[0] * G[0] + G[1] * G[1]) / v *d[0];


                d[1] = -G[1] + (G[0] * G[0] + G[1] * G[1]) / v *d[1];
            }
            //determinarea lui Alfa(k) dupa formula pagina 17
            Ak = -(G[0] * d[0] + G[1] * d[1]) /
                    (2 * a * d[0] * d[0] + 4 * d[0] * d[1] + 2 * b * d[1] * d[1]);
            Z[0] = X[0];
            Z[1] = X[1];
            A[0] = G[0];
            A[1] = G[1];
            //construim o aproximatie noua
            X[0] = X[0] + Ak * d[0];
            X[1] = X[1] + Ak * d[1];

            //
            if (X[0] == Z[0] && X[1] == Z[1])
                break;
            calculatedGradient();
        }

        labelTitleAlgoritm.setText(" Metoda Hestenes-Stiefel");

        textFieldCoordonataX.setText(String.valueOf(X[0]));
        textFieldCoordonataY.setText(String.valueOf(X[1]));
        textFieldFunctiaX.setText(String.valueOf(F(X[0], X[1])));
        textFieldIteratii.setText(String.valueOf(iter));


    }

    //valorile date in interfata grafica
    public void val_a_b() {
        ALFA = Float.parseFloat(textFieldPasulAlfa.getText());
        a = Float.parseFloat(textFieldValoareaA.getText());
        b = Float.parseFloat(textFieldValoareaB.getText());
    }

    //calcularea functie(x1,x2) in exemplu nostru
    public float F(float x1, float x2) {
        val_a_b();
//        float sum = a * x1 * x1 + 2 * x1 * x2 + b * x2 * x2 - 2 * x1 - 3 * x2;
//        System.out.println("sum" + sum);
        return a * x1 * x1 + 2 * x1 * x2 + b * x2 * x2 - 2 * x1 - 3 * x2;
    }
    //calcularea derivatei Functiei X
    public void calculatedGradient() {

        val_a_b();
        G[0] = 2 * a * X[0] + 2 * X[1] - 2;
        G[1] = 2 * X[0] + 2 * b * X[1] - 3;
    }

@FXML
    public void handleButtonClear(ActionEvent actionEvent) {
        textFieldValoareaA.setText("");
        textFieldValoareaB.setText("");
        textFieldPasulAlfa.setText("");
        labelTitleAlgoritm.setText("");
        textFieldCoordonataX.setText("");
        textFieldCoordonataY.setText("");
        textFieldFunctiaX.setText("");
        textFieldIteratii.setText("");

    }
}