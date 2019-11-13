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
@Table(name = "lecturers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturers.findAll", query = "SELECT l FROM Lecturers l")
    , @NamedQuery(name = "Lecturers.findById", query = "SELECT l FROM Lecturers l WHERE l.id = :id")
    , @NamedQuery(name = "Lecturers.findByFirstName", query = "SELECT l FROM Lecturers l WHERE l.firstName = :firstName")
    , @NamedQuery(name = "Lecturers.findByLastName", query = "SELECT l FROM Lecturers l WHERE l.lastName = :lastName")
    , @NamedQuery(name = "Lecturers.findByEmail", query = "from Lecturers l WHERE l.email = :email")
    , @NamedQuery(name = "Lecturers.findByPassword", query = "SELECT l FROM Lecturers l WHERE l.password = :password")
    , @NamedQuery(name = "Lecturers.findByTitle", query = "SELECT l FROM Lecturers l WHERE l.title = :title")
    , @NamedQuery(name = "Lecturers.findByUniversity", query = "SELECT l FROM Lecturers l WHERE l.university = :university")
    , @NamedQuery(name = "Lecturers.findBySpecialization", query = "SELECT l FROM Lecturers l WHERE l.specialization = :specialization")
    , @NamedQuery(name = "Lecturers.findByCreatedAt", query = "SELECT l FROM Lecturers l WHERE l.createdAt = :createdAt")
    , @NamedQuery(name = "Lecturers.findByUpdatedAt", query = "SELECT l FROM Lecturers l WHERE l.updatedAt = :updatedAt")})
public class Lecturers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "university")
    private String university;
    @Basic(optional = false)
    @Column(name = "Specialization")
    private String specialization;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Lecturers() {
    }

    public Lecturers(Integer id) {
        this.id = id;
    }

    public Lecturers(Integer id, String firstName, String lastName, String email, String password, String title, String university, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.title = title;
        this.university = university;
        this.specialization = specialization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
        if (!(object instanceof Lecturers)) {
            return false;
        }
        Lecturers other = (Lecturers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "attendance.actors.Lecturers[ id=" + id + " ]";
    }
    
}
