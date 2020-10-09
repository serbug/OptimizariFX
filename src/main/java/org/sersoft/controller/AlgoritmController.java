package org.sersoft.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sersoft.services.ControllerManager;

public class AlgoritmController implements Initializable {



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



    public  float GAMA= 1/2;
    public  float DELTA= (float) 0.05;
    public  float EPS= (float) Math.pow(10,-5);
    public  float[] X=new float[2];
    //Variabila Z este o variabila intermediara a lui X ^ k+1
    public  float[] Z=new float[2];
    public  float[] G=new float[2];



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
    public void handleButtonGradientul(ActionEvent actionEvent) {
        //numarul de iteratii
        int n=0;
        float Ak;
       float ALFA=Float.parseFloat(textFieldPasulAlfa.getText());

        X[0]=1;
        X[1]=1;


        calculareGradient();
//       if(G[0]==0||G[0]<=EPS&&G[1]==0||G[1]<=EPS){
//           System.out.println("Solutia optimala la moment");
//       }else {

           while (Math.sqrt(G[0] + G[0] * G[1] * G[1]) >= EPS) {
               ++n;

               //antigradientul ca directie de deplasare
               Z[0] = X[0] - ALFA * G[0];
               Z[1] = X[1] - ALFA * G[1];
               while ((F(Z[0], Z[1]) - F(X[0], X[1])) <= -DELTA * ALFA * (Math.pow(G[0],2)+Math.pow(G[1],2))) {
                   ALFA *= GAMA;
                   Z[0] = X[0] - ALFA * G[0];
                   Z[1] = X[1] - ALFA * G[1];
               }
               System.out.println("function "+F(X[0],X[1]));
               Ak = ALFA; //daca este indeplinita inegalitatea, atunci valoarea ALFA este acceptata si se va lua Ak=ALFA

               X[0] = X[0] - Ak * G[0];
               X[1] = X[1] - Ak * G[1];
               calculareGradient();
           }
     //  }
        labelTitleAlgoritm.setText("Metoda Gradientului cu fractionarea pasului");

        textFieldCoordonataX.setText(String.valueOf(X[0]));
        textFieldCoordonataY.setText(String.valueOf(X[1]));
        textFieldFunctiaX.setText(String.valueOf(F(X[0],X[1])));
        textFieldIteratii.setText(String.valueOf(n));


    }
    @FXML
    public void handleButtonHestenes(ActionEvent actionEvent) {

        int a=Integer.parseInt(textFieldValoareaA.getText());
        int b=Integer.parseInt(textFieldValoareaB.getText());

        int n=0, k=0;
        float Ak;
        //matrice simetrica pozitiva
        float[] A=new float[2];
        //directia
        float[] d=new float[2];
        float[] X1=new float[2];


        X[0] = 1;
        X[1] = 1;

        calculareGradient();
        //daca gradientul = 0 atunci x* = x^(k+1) este o solutie optima.
        while ((G[0] != 0 && G[1] != 0)) {
            ++n;

            if (k++ == 0) {

                d[0] = -G[0]; d[1] = -G[1];

            }else {
                float v = A[0] * A[0] + A[1] * A[1];

                d[0] = d[0] * (G[0] * G[0] + G[1] * G[1]) / v -
                        G[0];
                d[1] = d[1] * (G[0] * G[0] + G[1] * G[1]) / v - G[1];
            }
            //determinarea lui Alfa(k) dupa formula pagina 17
            Ak = -(G[0] * d[0] + G[1] * d[1]) /
                    (2 * a*d[0] * d[0] + 4 * d[0] * d[1] + 2 * b*d[1] * d[1]);
            X1[0] = X[0];
            X1[1] = X[1];
            A[0] = G[0];
            A[1] = G[1];
            //construim o aproximatie noua
            X[0] = X[0] + Ak * d[0];
            X[1] = X[1] + Ak * d[1];

            //
            if (X[0] == X1[0] && X[1] == X1[1])
                break;
            calculareGradient();
        }

        labelTitleAlgoritm.setText("Hestenes-STIEFEL");

        textFieldCoordonataX.setText(String.valueOf(X[0]));
        textFieldCoordonataY.setText(String.valueOf(X[1]));
        textFieldFunctiaX.setText(String.valueOf(F(X[0],X[1])));
        textFieldIteratii.setText(String.valueOf(n));




    }
    //calcularea functie(x1,x2) in exemplu nostru
     public float F(float x, float y){
         int a=Integer.parseInt(textFieldValoareaA.getText());
         int b=Integer.parseInt(textFieldValoareaB.getText());
         float sum = a*x*x+2*x*y+b*y*y-2*x-3*y;
         System.out.println("sum"+sum);
        return  a*x*x+2*x*y+b*y*y-2*x-3*y;
    }

     public void calculareGradient(){
         int a=Integer.parseInt(textFieldValoareaA.getText());
         int b=Integer.parseInt(textFieldValoareaB.getText());

        G[0]=2*a*X[0]+2*X[1]-2;
        G[1]=2*X[0]+2*b*X[1]-3;
    }

}