/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

import attendance.actors.Lecturers;
import attendance.models.LecturersModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    private Label message;

    String externalMessage;

    private LecturersModel lectureModel = new LecturersModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {

            message.setText(externalMessage);

        });

    }

    /*==================================
    |
    |        User Authentication
    |Retrieve from database and verify
    |==================================
    |
     */
    public void handleLogin(ActionEvent event) {
        System.out.println(username.getText());
        System.out.println(password.getText());

        String user = username.getText();
        String pass = password.getText();
        if (user.isEmpty() && pass.isEmpty()) {
            message.setText("Please enter your email address and password");
        } else {
            Lecturers lecturer = new Lecturers();
            lecturer = lectureModel.findLecturer(user, pass);

            try {
                if (lecturer.getId() != null) {

                    Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    oldStage.close();

                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/HomeFXML.fxml"));
                    Parent root = loader.load();
                    HomeFXMLController controller = loader.getController();
                    controller.setLastName(lecturer.getLastName());
                    controller.setTitle(lecturer.getTitle());
                    controller.setLeturerId(lecturer.getId());
                    
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();

                }

            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
                message.setText("Wrong username or password");

            }
        }

    }

    public void handleCancel(ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/attendance/views/WelcomeFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void setExternalMessage(String externalMessage) {
        this.externalMessage = externalMessage;
    }

}
