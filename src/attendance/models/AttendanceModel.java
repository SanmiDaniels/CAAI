/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import attendance.actors.Attendance;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author MoFoLuWaSo
 */
public class AttendanceModel extends AbstractModel<Attendance> {
 
     StatelessSession session;
    
    public AttendanceModel(){
        super(Attendance.class);
    }
    
    public Attendance findAttendance(Integer courseId, Integer lecturerId, Date date) {

        this.findSession();
        Query query = session.createQuery("from Attendance where courseId = :courseId and lecturerId = :lecturerId and date = :date");

        query.setInteger("lecturerId", lecturerId);
        query.setInteger("courseId", courseId);
        query.setDate("date", date);

        return (Attendance) query.uniqueResult();

    }
    
    public List<Attendance> findAttendanceList(Integer courseId, Integer lecturerId) {

        this.findSession();
        Query query = session.createQuery("from Attendance where courseId = :courseId and lecturerId = :lecturerId");

        query.setInteger("lecturerId", lecturerId);
        query.setInteger("courseId", courseId);
        

        return query.list();

    }
    
    public Long counttAttendanceSession(Integer courseId, Integer lecturerId) {

        this.findSession();
        return (Long) session.createCriteria(Attendance.class)
                .add(Restrictions.and(Restrictions.eq("courseId", courseId),
                        Restrictions.eq("lecturerId", lecturerId)))
                .setProjection(Projections.count("id")).uniqueResult();
        

        

    }
    
     
    @Override
    public void findSession() {

        try {

            session = sessionFactory.openStatelessSession();

        } catch (Exception e) {
        }
    }
}
