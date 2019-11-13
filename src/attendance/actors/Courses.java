/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.actors;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mofoluwaso
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c")
    , @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id")
    , @NamedQuery(name = "Courses.findByLecturerId", query = "SELECT c FROM Courses c WHERE c.lecturerId = :lecturerId")
    , @NamedQuery(name = "Courses.findByCourseCode", query = "SELECT c FROM Courses c WHERE c.courseCode = :courseCode")
    , @NamedQuery(name = "Courses.findByCourseUnit", query = "SELECT c FROM Courses c WHERE c.courseUnit = :courseUnit")
    , @NamedQuery(name = "Courses.findByCourseStatus", query = "SELECT c FROM Courses c WHERE c.courseStatus = :courseStatus")
    , @NamedQuery(name = "Courses.findByClassroomSessionNo", query = "SELECT c FROM Courses c WHERE c.classroomSessionNo = :classroomSessionNo")
    , @NamedQuery(name = "Courses.findByClassSessionUsed", query = "SELECT c FROM Courses c WHERE c.classSessionUsed = :classSessionUsed")
    , @NamedQuery(name = "Courses.findBySessionTaken", query = "SELECT c FROM Courses c WHERE c.sessionTaken = :sessionTaken")
    , @NamedQuery(name = "Courses.findByCourseHours", query = "SELECT c FROM Courses c WHERE c.courseHours = :courseHours")
    , @NamedQuery(name = "Courses.findByQualifyPercent", query = "SELECT c FROM Courses c WHERE c.qualifyPercent = :qualifyPercent")
    , @NamedQuery(name = "Courses.findByStartDate", query = "SELECT c FROM Courses c WHERE c.startDate = :startDate")
    , @NamedQuery(name = "Courses.findByEndDate", query = "SELECT c FROM Courses c WHERE c.endDate = :endDate")
    , @NamedQuery(name = "Courses.findByEnrollment", query = "SELECT c FROM Courses c WHERE c.enrollment = :enrollment")
    , @NamedQuery(name = "Courses.findByCreatedAt", query = "SELECT c FROM Courses c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "Courses.findByUpdatedAt", query = "SELECT c FROM Courses c WHERE c.updatedAt = :updatedAt")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "lecturer_id")
    private int lecturerId;
    @Basic(optional = false)
    @Lob
    @Column(name = "course_title")
    private String courseTitle;
    @Basic(optional = false)
    @Column(name = "course_code")
    private String courseCode;
    @Basic(optional = false)
    @Column(name = "course_unit")
    private int courseUnit;
    @Basic(optional = false)
    @Column(name = "course_status")
    private String courseStatus;
    @Basic(optional = false)
    @Column(name = "classroom_session_no")
    private int classroomSessionNo;
    @Basic(optional = false)
    @Column(name = "class_session_used")
    private int classSessionUsed;
    @Basic(optional = false)
    @Column(name = "session_taken")
    private String sessionTaken;
    @Basic(optional = false)
    @Column(name = "course_hours")
    private String courseHours;
    @Basic(optional = false)
    @Column(name = "qualify_percent")
    private int qualifyPercent;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "enrollment")
    private int enrollment;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Courses() {
    }

    public Courses(Integer id) {
        this.id = id;
    }

    public Courses(Integer id, int lecturerId, String courseTitle, String courseCode, int courseUnit, String courseStatus, int classroomSessionNo, int classSessionUsed, String sessionTaken, String courseHours, int qualifyPercent, Date startDate, Date endDate, int enrollment) {
        this.id = id;
        this.lecturerId = lecturerId;
        this.courseTitle = courseTitle;
        this.courseCode = courseCode;
        this.courseUnit = courseUnit;
        this.courseStatus = courseStatus;
        this.classroomSessionNo = classroomSessionNo;
        this.classSessionUsed = classSessionUsed;
        this.sessionTaken = sessionTaken;
        this.courseHours = courseHours;
        this.qualifyPercent = qualifyPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollment = enrollment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(int courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getClassroomSessionNo() {
        return classroomSessionNo;
    }

    public void setClassroomSessionNo(int classroomSessionNo) {
        this.classroomSessionNo = classroomSessionNo;
    }

    public int getClassSessionUsed() {
        return classSessionUsed;
    }

    public void setClassSessionUsed(int classSessionUsed) {
        this.classSessionUsed = classSessionUsed;
    }

    public String getSessionTaken() {
        return sessionTaken;
    }

    public void setSessionTaken(String sessionTaken) {
        this.sessionTaken = sessionTaken;
    }

    public String getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(String courseHours) {
        this.courseHours = courseHours;
    }

    public int getQualifyPercent() {
        return qualifyPercent;
    }

    public void setQualifyPercent(int qualifyPercent) {
        this.qualifyPercent = qualifyPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "attendance.actors.Courses[ id=" + id + " ]";
    }
    
}
