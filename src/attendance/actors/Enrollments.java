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
@Table(name = "enrollments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrollments.findAll", query = "SELECT e FROM Enrollments e")
    , @NamedQuery(name = "Enrollments.findById", query = "SELECT e FROM Enrollments e WHERE e.id = :id")
    , @NamedQuery(name = "Enrollments.findByCourseId", query = "SELECT e FROM Enrollments e WHERE e.courseId = :courseId")
    , @NamedQuery(name = "Enrollments.findByLecturerId", query = "SELECT e FROM Enrollments e WHERE e.lecturerId = :lecturerId")
    , @NamedQuery(name = "Enrollments.findByMatric", query = "SELECT e FROM Enrollments e WHERE e.matric = :matric")
    , @NamedQuery(name = "Enrollments.findByLastName", query = "SELECT e FROM Enrollments e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Enrollments.findByFirstName", query = "SELECT e FROM Enrollments e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Enrollments.findByEmail", query = "SELECT e FROM Enrollments e WHERE e.email = :email")
    , @NamedQuery(name = "Enrollments.findByParentEmail", query = "SELECT e FROM Enrollments e WHERE e.parentEmail = :parentEmail")
    , @NamedQuery(name = "Enrollments.findByTelephone", query = "SELECT e FROM Enrollments e WHERE e.telephone = :telephone")
    , @NamedQuery(name = "Enrollments.findByCreatedAt", query = "SELECT e FROM Enrollments e WHERE e.createdAt = :createdAt")
    , @NamedQuery(name = "Enrollments.findByUpdatedAt", query = "SELECT e FROM Enrollments e WHERE e.updatedAt = :updatedAt")})
public class Enrollments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "course_id")
    private int courseId;
    @Basic(optional = false)
    @Column(name = "lecturer_id")
    private int lecturerId;
    @Basic(optional = false)
    @Column(name = "matric")
    private String matric;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "parent_email")
    private String parentEmail;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Enrollments() {
    }

    public Enrollments(Integer id) {
        this.id = id;
    }

    public Enrollments(Integer id, int courseId, int lecturerId, String matric, String lastName, String firstName, String email, String parentEmail, String telephone) {
        this.id = id;
        this.courseId = courseId;
        this.lecturerId = lecturerId;
        this.matric = matric;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.parentEmail = parentEmail;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getMatric() {
        return matric;
    }

    public void setMatric(String matric) {
        this.matric = matric;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        if (!(object instanceof Enrollments)) {
            return false;
        }
        Enrollments other = (Enrollments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "attendance.actors.Enrollments[ id=" + id + " ]";
    }
    
}
