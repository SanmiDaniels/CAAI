/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

import attendance.actors.Lecturers;
import attendance.models.LecturersModel;
import attendance.services.DateFormaterUtil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class RegisterFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> title;

    @FXML
    private ComboBox<String> institution;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField specialization;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirm;

    @FXML
    private Label message;

    private LecturersModel lectureModel = new LecturersModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        title.setItems(FXCollections.observableArrayList("Prof.", "Dr", "Mr", "Mrs", "Miss"));
        institution.setItems(FXCollections.observableArrayList("University of Ibadan", "Obafemi Awolowo University"));

    }

    /*=======================================
    |Register a Lecturer and Request Login
    |====================================
    |Lecturer register to use the system
    |on success the lecturer is asked
    |to login
    |
    
     */
    @FXML
    public void register(ActionEvent event) {

        if (this.validateInformation()) {
            try {

                Lecturers lecturer = new Lecturers();
                lecturer.setFirstName(firstName.getText().trim());
                lecturer.setLastName(lastName.getText().trim());
                lecturer.setEmail(email.getText().toLowerCase().trim());
                lecturer.setPassword(password.getText().trim());
                lecturer.setTitle(title.getValue());
                lecturer.setUniversity(institution.getValue());
                lecturer.setSpecialization(specialization.getText().trim());
                lecturer.setCreatedAt(DateFormaterUtil.todaySQLDate());
                lecturer.setUpdatedAt(DateFormaterUtil.todaySQLDate());

                lectureModel.create(lecturer);
                message.setText("Registration Successful");
                
                Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();
FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/LoginFXML.fxml"));
        Parent root = loader.load();
LoginFXMLController controller = loader.getController();
controller.setExternalMessage("Congratulations! You have successfully Registered, please login with your email and password");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
                
                
                
                } catch (Exception e) {
                message.setText("There was a problem granting your supplied information");
            }

        }

//        System.out.println("Title: "+title.getValue());
//        System.out.println("Institution: "+institution.getValue());
    }

    @FXML
    public void cancelRegister(ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/attendance/views/WelcomeFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    public boolean validateInformation() {

        boolean pass = true;

        if (title.getValue() == null) {
            message.setText("Please Select a title");
            return false;
        }

        if (institution.getValue() == null) {
            message.setText("Please Select an institution");
            return false;
        }

        if (firstName.getText().isEmpty()) {
            message.setText("Please Provide a first name");
            return false;
        }

        if (lastName.getText().isEmpty()) {
            message.setText("Please Provide a last name");
            return false;
        }

        if (specialization.getText().isEmpty()) {
            message.setText("Please enter your area of specialization");
            return false;
        }

        Lecturers oldlecturer = new Lecturers();

        if (!email.getText().isEmpty()) {

            if (!email.getText().toLowerCase().trim().contains("@")) {
                message.setText("Please Provide a valid email address");
                return false;
            }
            oldlecturer = lectureModel.findByEmail(email.getText().toLowerCase().trim());
            try {
                if (oldlecturer.getId() > 0) {

                    message.setText("Email address already exist");
                    return false;
                }
            } catch (Exception e) {
            }

        } else {
            message.setText("Please supply an email address");
            return false;
        }

        if (password.getText().trim().isEmpty()) {
            message.setText("Please provide a password");
            return false;
        } else {

            if (!password.getText().trim().equals(passwordConfirm.getText().trim())) {
                message.setText("Password did not match the confirmation password");
                return false;
            }

        }

        return pass;

    }

}
