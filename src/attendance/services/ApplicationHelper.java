/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.services;

import attendance.actors.Attendance;
import attendance.actors.Courses;
import attendance.actors.Enrollments;
import attendance.models.AttendanceModel;
import attendance.models.CoursesModel;
import attendance.models.EnrollmentsModel;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author mofoluwaso
 */
public class ApplicationHelper {

//    public static void main(String[] args) {
//        String participants = "21,34,55";
//        String[] participate = participants.split(",");
//        List<String> listParticipants = new ArrayList<>();
//        listParticipants = Arrays.asList(participate);
//        System.out.println(listParticipants.indexOf("2"));
//    }
    AttendanceModel attendModel = new AttendanceModel();
    CoursesModel courseModel = new CoursesModel();
    EnrollmentsModel enrollModel = new EnrollmentsModel();

    public boolean processAttendance(Integer courseId, Integer lecturerId, Date date, String matric) {
        Attendance oldAttendance = new Attendance();
        oldAttendance = attendModel.findAttendance(courseId, lecturerId, date);
        try {

            if (oldAttendance.getId() != null) {

                String participants = oldAttendance.getParticipants();
                if (participants.isEmpty()) {
                    oldAttendance.setParticipants(matric);
                    attendModel.update(oldAttendance);
                    return true;
                } else {
                    try {
                        String[] participate = participants.split(",");
                        List<String> listParticipants = new ArrayList<>();
                        listParticipants = Arrays.asList(participate);
                        int index = listParticipants.indexOf(matric);
                        if (index >= 0) {
                            return false;
                        } else {
                            listParticipants.add(matric);
                            oldAttendance.setParticipants(this.arrayToString(listParticipants));
                            attendModel.update(oldAttendance);
                            return true;
                        }
                    } catch (Exception e) {
                        if (!oldAttendance.getParticipants().equals(matric)) {

                            oldAttendance.setParticipants(oldAttendance.getParticipants() + "," + matric);
                            attendModel.update(oldAttendance);
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

            }

        } catch (Exception e) {
        }

        try {

            Attendance attendance = new Attendance();
            attendance.setCourseId(courseId);
            attendance.setLecturerId(lecturerId);
            attendance.setDate(date);
            attendance.setCreatedAt(DateFormaterUtil.sqlDate(DateFormaterUtil.todaysDate()));
            attendance.setUpdatedAt(DateFormaterUtil.sqlDate(DateFormaterUtil.todaysDate()));
            attendance.setParticipants(matric);
            attendModel.create(attendance);
            return true;

        } catch (Exception e) {
        }
        return false;
    }

    public String arrayToString(List<String> attendees) {
        String attend = "";
        for (String attendee : attendees) {
            attend += attendee + ",";
        }
        return attend.substring(0, attend.length() - 1);
    }

    public void updateCourseEnrollment(Integer courseId, Integer lecturerId) {
        Courses course = new Courses();
        course = courseModel.findByLecturerCourseId(lecturerId, courseId);
        Long num = enrollModel.counttEnrollmentsList(courseId, lecturerId);
        course.setEnrollment(Integer.parseInt(num.toString()));
        courseModel.update(course);
    }

    public void updateCourseAttendance(Integer courseId, Integer lecturerId) {
        Courses course = new Courses();
        course = courseModel.findByLecturerCourseId(lecturerId, courseId);
        Long num = attendModel.counttAttendanceSession(courseId, lecturerId);
        course.setClassSessionUsed(Integer.parseInt(num.toString()));
        courseModel.update(course);
    }

    public StudentStatistics studentEnrollmentInfo(Integer courseId, Integer lecturerId, String matric) {
       Enrollments enrol = new Enrollments();
       enrol = enrollModel.findStudentEnrollments(courseId, lecturerId, matric);
       int [] st = retrieveStudentAttendanceStat(courseId, lecturerId, matric);
       
       StudentStatistics newStat = new StudentStatistics();
       newStat.setFirstName(enrol.getFirstName());
       newStat.setLastName(enrol.getLastName());
       newStat.setMatric(matric);
       newStat.setClassAttended(st[1]);
       newStat.setTotalClass(st[0]);

       return newStat;
    }

    public int[] retrieveStudentAttendanceStat(Integer courseId, Integer lecturerId, String matric) {
        List<Attendance> attendees = new ArrayList<>();
        attendees = attendModel.findAttendanceList(courseId, lecturerId);
        int totalClass = 0;
        int totalAttended = 0;
        for (Attendance attendee : attendees) {
            totalClass++;
            String participants = attendee.getParticipants();

            try {
                String[] participate = participants.split(",");
                List<String> listParticipants = new ArrayList<>();
                listParticipants = Arrays.asList(participate);
                int index = listParticipants.indexOf(matric);
                if (index >= 0) {
                    totalAttended++;
                }
            } catch (Exception e) {
                if (participants.equals(matric)) {
                    totalAttended++;

                }
            }
        }
        int[] stat = new int[2];
        stat[0] = totalClass;
        stat[1] = totalAttended;
        return stat;
    }
}
