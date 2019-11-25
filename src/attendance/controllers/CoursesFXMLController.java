/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.controllers;

import attendance.actors.Courses;
import attendance.models.CoursesModel;
import attendance.services.DateFormaterUtil;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author mofoluwaso
 */
public class CoursesFXMLController implements Initializable {

    private Integer leturerId;

    private String title;

    private String lastName;

    private int viewOptions = 0;

    @FXML
    private Label message;

    @FXML
    private Label courseMessage;

    @FXML
    private Label courseOptionMessage;

    private Window homeWindow;

    private Window courseWindow;

    @FXML
    private TextField courseTitle;

    @FXML
    private TextField courseCode;

    @FXML
    private Slider courseUnit;

    @FXML
    private Label courseUnitLabel;

    @FXML
    private TextField courseStatus;

    @FXML
    private Slider classSession;

    @FXML
    private Label classSessionLabel;

    @FXML
    private TextField courseHours;

    @FXML
    private Slider qualifyPercent;

    @FXML
    private Label qualifyPercentLabel;

    @FXML
    private ComboBox<String> sessionTaken;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    TableView<Courses> courseTable = new TableView<>();

    Courses realCourse;
    private CoursesModel courseModel = new CoursesModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            classSession.setValue(13);
            classSessionLabel.setText(Integer.toString(13));
            classSession.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    int num = (int) Double.parseDouble(newValue.toString());

                    classSessionLabel.setText(Integer.toString(num));
                }
            });

            qualifyPercent.valueProperty().addListener((o, ov, nv) -> {
                int num = (int) Double.parseDouble(nv.toString());
                qualifyPercentLabel.setText(Integer.toString(num));
            });

            courseUnit.valueProperty().addListener((o, ov, nv) -> {
                int num = (int) Double.parseDouble(nv.toString());
                courseUnitLabel.setText(Integer.toString(num));
            });

            sessionTaken.setItems(FXCollections.observableArrayList("2017/18", "2018/19", "2019/20", "2020/21", "2021/22"));

        } catch (Exception e) {
        }
        Platform.runLater(() -> {

            if (viewOptions == 1) {
                List<Courses> courses = new ArrayList<>();

                courses = courseModel.findByLecturerId(this.leturerId);
                courseTable.setItems(FXCollections.observableArrayList(courses));

                TableColumn<Courses, String> cTitle = new TableColumn<>("Course Title");
                cTitle.setCellValueFactory(new PropertyValueFactory("courseTitle"));

                TableColumn<Courses, String> cCode = new TableColumn<>("Course Code");
                cCode.setCellValueFactory(new PropertyValueFactory("courseCode"));

                TableColumn<Courses, Integer> cUnit = new TableColumn<>("Course Unit");
                cUnit.setCellValueFactory(new PropertyValueFactory("courseUnit"));

                TableColumn<Courses, String> cSession = new TableColumn<>("Session");
                cSession.setCellValueFactory(new PropertyValueFactory("sessionTaken"));

                TableColumn<Courses, Integer> clSession = new TableColumn<>("No. of Class Session");
                clSession.setCellValueFactory(new PropertyValueFactory("classroomSessionNo"));

                TableColumn<Courses, Integer> sessUsed = new TableColumn<>("No. of Session Used");
                sessUsed.setCellValueFactory(new PropertyValueFactory("classSessionUsed"));

                TableColumn<Courses, Integer> sEnroll = new TableColumn<>("Students Enrollment");
                sEnroll.setCellValueFactory(new PropertyValueFactory("enrollment"));

                TableColumn<Courses, Date> sDate = new TableColumn<>("Start Date");
                sDate.setCellValueFactory(new PropertyValueFactory("startDate"));

                courseTable.getColumns().setAll(cTitle, cCode, cUnit, cSession, clSession, sessUsed, sEnroll, sDate);

            }
        });

    }

    @FXML
    public void handleBack(ActionEvent event) {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        Stage stage = (Stage) homeWindow;
        stage.show();
    }

    @FXML
    public void handleSubmit(ActionEvent event) throws ParseException {

        if (this.validateInformation()) {
            Courses course = new Courses();
            course.setLecturerId(leturerId);
            course.setCourseTitle(courseTitle.getText());
            course.setCourseCode(courseCode.getText());
            course.setCourseUnit((int) courseUnit.getValue());
            course.setCourseStatus(courseStatus.getText());
            course.setClassroomSessionNo((int) classSession.getValue());
            course.setSessionTaken(sessionTaken.getValue());
            course.setCourseHours(courseHours.getText());
            course.setQualifyPercent((int) qualifyPercent.getValue());

            course.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate.getValue().toString()));
            course.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate.getValue().toString()));
            course.setUpdatedAt(DateFormaterUtil.todaySQLDate());
            course.setCreatedAt(DateFormaterUtil.todaySQLDate());
            course.setEnrollment(0);
            courseModel.create(course);
            message.setText("Course Successfully Registered");

        }

    }

    public boolean validateInformation() {
        boolean pass = true;

        if (courseTitle.getText().isEmpty()) {
            message.setText("You need to provide a course title");
            return false;
        }

        if (courseCode.getText().isEmpty()) {
            message.setText("Enter a course code");
            return false;
        }

        if (courseUnit.getValue() < 2) {
            message.setText("Course Unit must be minimum of 2");
            return false;
        }

        if (courseStatus.getText().isEmpty()) {
            message.setText("Course status must be entered .e.g Compulsory or Elective");
            return false;
        }

        if (classSession.getValue() < 5) {
            message.setText("Number of Classes for the Course must be more than 4");
            return false;
        }

        if (courseHours.getText().isEmpty()) {
            message.setText("You need to enter the maximum number of hours for the course");
            return false;
        }

        if (qualifyPercent.getValue() < 50) {
            message.setText("Attendance Criteria for examination must be mmore than or exactly 50%");
            return false;
        }

        if (startDate.getValue() == null) {
            message.setText("You must provide the date course is starting");
            return false;
        }

        if (endDate.getValue() == null) {
            message.setText("You must provide the date course is ending");
            return false;
        }

        if (!courseCode.getText().isEmpty() & sessionTaken.getValue() != null) {
            Courses theCourse = new Courses();
            theCourse = courseModel.findCourseByCodeSession(courseCode.getText(), sessionTaken.getValue());
            try {
                if (theCourse.getId() > 0) {
                    message.setText("This course has already been created.");
                    return false;
                }
            } catch (Exception e) {
            }
        }

        return pass;
    }

    @FXML
    public void handleCourseSelection(MouseEvent event) {

        if (event.getClickCount() == 2) {
            try {

                Courses selectedCourse = courseTable.getSelectionModel().getSelectedItem();
                //courseOptionMessage.setText(selectedCourse.getCourseCode() + ": " + selectedCourse.getCourseTitle());

                Window courseWin = ((Node) event.getSource()).getScene().getWindow();

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/CourseOptionsFXML.fxml"));
                Parent root = loader.load();
                CoursesFXMLController controller = loader.getController();
                controller.setHomeWindow(homeWindow);
                controller.setCourseWindow(courseWin);
                controller.setLastName(lastName);
                controller.setLeturerId(leturerId);
                controller.setTitle(title);
                controller.setViewOptions(1);
                controller.setRealCourse(selectedCourse);
                controller.setCourseOptionMessage(selectedCourse.getCourseCode() + ": " + selectedCourse.getCourseTitle());
                Stage oldStage = (Stage) courseWin;
                oldStage.close();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {

            }

        }

    }

    @FXML
    public void handleBackCourseOptions(ActionEvent event) {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
        Stage stage = (Stage) courseWindow;
        stage.show();

    }

    @FXML
    public void enrollStudents(ActionEvent event) {
        Window courseOptionWindow = ((Node) event.getSource()).getScene().getWindow();
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/StudentCourseEnrollmentFXML.fxml"));
            Parent root = loader.load();

            StudentsFXMLController controller = loader.getController();
            controller.setCourseOptionWindow(courseOptionWindow);
            controller.setCourse(realCourse);
            controller.setBy(title + " " + lastName);

            courseOptionWindow.hide();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }

    }

    @FXML
    public void markAttendance(ActionEvent event) {

         Window courseOptionWindow = ((Node) event.getSource()).getScene().getWindow();
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/MarkAttendanceFXML.fxml"));
            Parent root = loader.load();

            StudentsFXMLController controller = loader.getController();
            controller.setCourseOptionWindow(courseOptionWindow);
            controller.setCourse(realCourse);
            controller.setBy(title + " " + lastName);

            courseOptionWindow.hide();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
    }

    @FXML
    public void viewEnrolledStudents(ActionEvent event) {

        Window courseOptionWindow = ((Node) event.getSource()).getScene().getWindow();
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/views/EnrolledStudentsFXML.fxml"));
            Parent root = loader.load();

        

            courseOptionWindow.hide();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }        
        
        
    }

    @FXML
    public void viewCourseDetails(ActionEvent event) {

    }

    @FXML
    public void attendanceStatistics(ActionEvent event) {

    }

    @FXML
    public void editCourse(ActionEvent event) {

    }

    @FXML
    public void removeCourse(ActionEvent event) {

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

    public void setHomeWindow(Window homeWindow) {
        this.homeWindow = homeWindow;
    }

    public void setViewOptions(int viewOptions) {
        this.viewOptions = viewOptions;
    }

    public void setCourseWindow(Window courseWindow) {
        this.courseWindow = courseWindow;
    }

    public void setCourseOptionMessage(String courseOptionMessage) {
        this.courseOptionMessage.setText(courseOptionMessage);
    }

    public void setRealCourse(Courses realCourse) {
        this.realCourse = realCourse;
    }

}
