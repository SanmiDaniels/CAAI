/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.services;

/**
 *
 * @author MoFoLuWaSo
 */
public class StudentStatistics {
    
    private String firstName;
    private String lastName;
    private String matric;
    private int classAttended;
    private int totalClass;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatric() {
        return matric;
    }

    public void setMatric(String matric) {
        this.matric = matric;
    }

    public int getClassAttended() {
        return classAttended;
    }

    public void setClassAttended(int classAttended) {
        this.classAttended = classAttended;
    }

    public int getTotalClass() {
        return totalClass;
    }

    public void setTotalClass(int totalClass) {
        this.totalClass = totalClass;
    }
    
    
}
