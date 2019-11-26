/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

import attendance.actors.Courses;
import attendance.actors.Enrollments;
import attendance.models.EnrollmentsModel;
import attendance.services.ApplicationHelper;
import attendance.services.DateFormaterUtil;
import attendance.services.FaceRecognizer;
import attendance.services.MarkAttendance;
import attendance.services.StudentStatistics;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class StudentsFXMLController implements Initializable {

    private Window courseOptionWindow;

    private Courses course = new Courses();

    private String by;

    @FXML
    private Label enrollLabel;

    @FXML
    private TextField matric;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField parentEmail;

    @FXML
    private TextField telephone;

    @FXML
    private Label message;

    @FXML
    private Label fullName;

    @FXML
    private Label sMatric;

    @FXML
    private Label attended;

    @FXML
    private Label held;

    @FXML
    private Label qualified;

    @FXML
    private Label totalClass;

    @FXML
    private PieChart pie;

    @FXML
    private BarChart bar;

    @FXML
    private LineChart line;

    @FXML
    private DatePicker today;
    private EnrollmentsModel enrollModel = new EnrollmentsModel();

    private ApplicationHelper helper = new ApplicationHelper();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {
            try {

                enrollLabel.setText(course.getCourseCode() + ": " + course.getCourseTitle());
            } catch (Exception e) {
            }

        });

    }

    public boolean validateInformation() {
        if (matric.getText().isEmpty()) {
            message.setText("Please provide student matric number");
            return false;
        }
        if (firstName.getText().isEmpty()) {
            message.setText("First name is required for enrollment");
            return false;
        }
        if (lastName.getText().isEmpty()) {
            message.setText("Last name is required for enrollment");
            return false;
        }
        if (email.getText().isEmpty()) {
            message.setText("Please enter student email address");
            return false;
        }
        if (parentEmail.getText().isEmpty()) {
            message.setText("Parent or Guardian email is needed in case student default");
            return false;
        }
        if (telephone.getText().isEmpty()) {
            message.setText("Student telephone number must be provided");
            return false;
        }
        return true;
    }

    @FXML
    public void enrollStudentOnly(ActionEvent event) {
        if (this.validateInformation()) {
            if (this.saveEnrollment()) {
                message.setText("Enrollment successfully Saved");
            } else {
                message.setText("Entry already exist!.");
            }
        }
    }

    @FXML
    public void enrollAndCaptureStudent(ActionEvent event) {
        if (this.validateInformation()) {
            try {
                if (new FaceRecognizer().recognizeFromCam(matric.getText())) {

                    if (this.saveEnrollment()) {
                        message.setText("Enrollment successfully Saved");
                    } else {
                        message.setText("Entry already exist! Face has been captured but enrollment could not be processed.");
                    }
                }

            } catch (InterruptedException ex) {
                //Logger.getLogger(StudentsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void captureStudentOnly(ActionEvent event) {
        if (matric.getText().isEmpty()) {
            message.setText("Please Enter student matric number in order to Train CAAI");

        } else {

            try {
                 Thread t = new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            
                     new FaceRecognizer().recognizeFromCam(matric.getText());
                message.setText("Face successfully Captured");

                        } catch (Exception ae) {
                           
                        }
                    }
                };
                t.start();

                
                new FaceRecognizer().recognizeFromCam(matric.getText());
                message.setText("Face successfully Captured");

//            try {
//                
//               Stage stage = new Stage();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/FaceCameraFXML.fxml"));
//            Parent root = loader.load();
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            } catch (Exception e) {
//            e.printStackTrace();
//            }
            } catch (InterruptedException ex) {

            }

        }
    }

    public boolean saveEnrollment() {

        Enrollments oldStudent = new Enrollments();
        oldStudent = enrollModel.findStudentEnrollments(course.getId(), course.getLecturerId(), matric.getText());
        try {
            oldStudent.getCourseId();
            return false;
        } catch (Exception e) {

            Enrollments student = new Enrollments();
            student.setCourseId(course.getId());
            student.setLecturerId(course.getLecturerId());
            student.setMatric(matric.getText());
            student.setLastName(lastName.getText());
            student.setFirstName(firstName.getText());
            student.setEmail(email.getText());
            student.setParentEmail(parentEmail.getText());
            student.setTelephone(telephone.getText());
            student.setCreatedAt(DateFormaterUtil.todaySQLDate());
            student.setUpdatedAt(DateFormaterUtil.todaySQLDate());
            enrollModel.create(student);

            helper.updateCourseEnrollment(course.getId(), course.getLecturerId());
        }
        return true;
    }

    @FXML
    public void backToCourseOptions(ActionEvent event) {

        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        Stage stage = (Stage) courseOptionWindow;
        stage.show();
    }

    @FXML
    public void markAttendance(ActionEvent event) {

    System.out.println("We got here1");
        
       MarkAttendance obj =  new MarkAttendance();
        
        obj.setOnRunning((succeesesEvent) -> {
                
            
               });

               obj.setOnSucceeded((succeededEvent) -> {
               markTruly(obj.getValue().toString());
                  System.out.println("We got here1"); 
               });

               ExecutorService executorService = Executors.newFixedThreadPool(1);
               executorService.execute(obj);
               executorService.shutdown();
        

    }
    
    
    public void markTruly(String matr){
        
        String matricNumber = matr;
        if (matricNumber.isEmpty()) {
            
            System.out.println("We got here1");
            message.setText("Student has not been enrolled for the course");
        } else {

            if (today.getValue() == null) {
                if (helper.processAttendance(course.getId(), course.getLecturerId(), DateFormaterUtil.todaySQLDate(), matricNumber)) {
                    helper.updateCourseAttendance(course.getId(), course.getLecturerId());
                    message.setText("Student successfully marked present");
                    
                    try {
                    updateStudentAttendanceStatistics(matricNumber);
                        
                    } catch (Exception e) {
                    }
                }
            } else {
                try {
                    if (helper.processAttendance(course.getId(), course.getLecturerId(), DateFormaterUtil.sqlDate(today.getValue().toString()), matricNumber)) {
                        helper.updateCourseAttendance(course.getId(), course.getLecturerId());
                        message.setText("Student successfully marked present");
                        updateStudentAttendanceStatistics(matricNumber);
                    }

                } catch (Exception e) {
                }
            }

        }
        
        
        
    }
    
  

    public void updateStudentAttendanceStatistics(String matric) {
        StudentStatistics statistics = new StudentStatistics();
        statistics = helper.studentEnrollmentInfo(course.getId(), course.getLecturerId(), matric);
        fullName.setText(statistics.getFirstName() + " " + statistics.getLastName());
        sMatric.setText(statistics.getMatric());
        //attended.setText(Integer.toString(statistics.getClassAttended()));
        int classSess = course.getClassroomSessionNo();
        attended.setText(Integer.toString((statistics.getClassAttended() * 100) / classSess)+"%");
        held.setText(Integer.toString(statistics.getTotalClass()));
        totalClass.setText(Integer.toString(course.getClassroomSessionNo()));

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Distribution for Student Attendance");

        int qual = course.getQualifyPercent();
        dataSeries1.getData().add(new XYChart.Data("Total Class Attended", (statistics.getClassAttended() * 100) / classSess));
        dataSeries1.getData().add(new XYChart.Data("Held", (statistics.getTotalClass() * 100) / classSess));
//        dataSeries1.getData().add(new XYChart.Data("Total Class Session", 100));
        dataSeries1.getData().add(new XYChart.Data("Bench Mark", qual));

        bar.getData().add(dataSeries1);
        line.getData().add(dataSeries1);

        //PieChart pieChart = new PieChart();
        int absent = 0;
        if (((statistics.getClassAttended() * 100) / classSess) > ((statistics.getTotalClass() * 100) / classSess)) {
            absent = ((statistics.getClassAttended() * 100) / classSess) - ((statistics.getTotalClass() * 100) / classSess);
        } else {
            absent = ((statistics.getTotalClass() * 100) / classSess) - ((statistics.getClassAttended() * 100) / classSess);
        }
        PieChart.Data slice1 = new PieChart.Data("Total Class Attended", (statistics.getClassAttended() * 100) / classSess);
        PieChart.Data slice2 = new PieChart.Data("Total Class Held", (statistics.getTotalClass() * 100) / classSess);
        PieChart.Data slice3 = new PieChart.Data("Absent", absent);

        
        pie.getData().add(slice1);
        pie.getData().add(slice2);
        pie.getData().add(slice3);

    }

    /*=======================
    | Setters
    |===============
     */
    public void setCourseOptionWindow(Window courseOptionWindow) {
        this.courseOptionWindow = courseOptionWindow;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public void setBy(String by) {
        this.by = by;
    }

}
