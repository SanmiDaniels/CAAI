/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class HomeFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Integer leturerId;

    private String title;

    private String lastName;

    @FXML
    private Label message;

    @FXML
    private ImageView imageview;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {

            message.setText(title + " " + lastName);
            
   
        });
    
    
    
    
    
    }

    @FXML
    public void handleTeach(ActionEvent event) {

        Window homeWindow = ((Node) event.getSource()).getScene().getWindow();
        homeWindow.hide();
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/NewCourseFXML.fxml"));
            Parent root = loader.load();
            CoursesFXMLController controller = loader.getController();
            controller.setLeturerId(leturerId);
            controller.setLastName(lastName);
            controller.setTitle(title);
            controller.setHomeWindow(homeWindow);
            controller.setViewOptions(0);

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
        }

    }

    @FXML
    public void viewCourses(ActionEvent event) throws IOException {
        Window homeWindow = ((Node) event.getSource()).getScene().getWindow();

        homeWindow.hide();

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/CoursesFXML.fxml"));
        Parent root = loader.load();
        CoursesFXMLController controller = loader.getController();

        controller.setHomeWindow(homeWindow);
        controller.setLastName(lastName);
        controller.setLeturerId(leturerId);
        controller.setTitle(title);
        controller.setViewOptions(1);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signout(ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/attendance/views/WelcomeFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void setLeturerId(Integer leturerId) {
        this.leturerId = leturerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
