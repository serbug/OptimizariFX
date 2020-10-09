package org.sersoft.services;

import org.sersoft.controller.AlgoritmController;
import org.sersoft.controller.LoginController;
import org.sersoft.controller.MainController;

public class ControllerManager {

    private static ControllerManager controllerManager;




    private static AlgoritmController algoritmController;
    private static MainController mainController;
    private static LoginController loginController;
  //  private static AddDateSiteController optionsController;


    private ControllerManager() {

    }

    public static ControllerManager getInstance() {
        if (controllerManager == null) {
            controllerManager = new ControllerManager();
        }
        return controllerManager;
    }

    public static ControllerManager getControllerManager() {
        return controllerManager;
    }

    public static void setControllerManager(ControllerManager aControllerManager) {
        controllerManager = aControllerManager;
    }

//    public static MainController getMainController() {
//        return mainController;
//    }
//
//    public static void setMainController(MainController aMainController) {
//        mainController = aMainController;
//    }
public static MainController getPrimaryController() {
    return mainController;
}

    public static void setPrimaryController(MainController mainController) {
        ControllerManager.mainController = mainController;
    }
    public static LoginController getLoginController() {
        return loginController;
    }

    public static void setLoginController(LoginController aLoginController) {
        loginController = aLoginController;
    }

    public static AlgoritmController getAlgoritmController() {
        return algoritmController;
    }

    public static void setAlgoritmController(AlgoritmController algoritmController) {
        ControllerManager.algoritmController = algoritmController;
    }

//    public static AddDateSiteController getOptionsController() {
//        return optionsController;
//    }
//
//    public static void setOptionsController(AddDateSiteController aOptionsController) {
//        optionsController = aOptionsController;
//    }






}
