/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import attendance.actors.Courses;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mofoluwaso
 */
public class CoursesModel extends AbstractModel<Courses>{
   
    
    StatelessSession session;
    
    public CoursesModel(){
        super(Courses.class);
    }
    
    
    public Courses findCourseByCodeSession(String code, String sess) {
        this.findSession();
        return (Courses) session.createCriteria(Courses.class)
                .add(Restrictions
                        .and(Restrictions.eq("courseCode", code),
                                Restrictions.eq("sessionTaken", sess))).uniqueResult();
       

    }
    
    public List<Courses> findByLecturerId(Integer lecturerId) {

        this.findSession();
        Query query = session.createQuery("from Courses where lecturerId = :lecturerId");

        query.setInteger("lecturerId", lecturerId);

        List<Courses> courseList = new ArrayList<>();
        courseList = query.list();
        return courseList;

    }
    
    public Courses findByLecturerCourseId(Integer lecturerId, Integer courseId) {

        this.findSession();
        Query query = session.createQuery("from Courses where lecturerId = :lecturerId and id = :id");

        query.setInteger("lecturerId", lecturerId);
        query.setInteger("id", courseId);

        
        return (Courses) query.uniqueResult();
        

    }
    
    @Override
    public void findSession() {

        try {

            session = sessionFactory.openStatelessSession();

        } catch (Exception e) {
        }
    }
}
