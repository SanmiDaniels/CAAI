/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author mofoluwaso
 */
public class WelcomeFXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button createAccount;
    @FXML
    private Button signin;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException{
        Stage oldStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldStage.close();
        
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/attendance/views/RegisterFXML.fxml"));
         
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void handleSignIn(ActionEvent event) throws IOException{
        
        
        Stage oldStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        oldStage.close();
        
        Stage stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/attendance/views/LoginFXML.fxml"));
         
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
