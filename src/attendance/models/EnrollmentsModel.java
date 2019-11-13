/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import attendance.actors.Enrollments;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mofoluwaso
 */
public class EnrollmentsModel extends AbstractModel<Enrollments> {

    StatelessSession session;

    public EnrollmentsModel() {
        super(Enrollments.class);
    }

    public Enrollments findStudentEnrollments(Integer courseId, Integer lecturerId, String matric) {

        this.findSession();
        Query query = session.createQuery("from Enrollments where courseId = :courseId and lecturerId = :lecturerId and matric = :matric");

        query.setInteger("lecturerId", lecturerId);
        query.setInteger("courseId", courseId);
        query.setString("matric", matric);

        return (Enrollments) query.uniqueResult();

    }

    public List<Enrollments> findStudentEnrollmentsList(Integer courseId, Integer lecturerId) {

        this.findSession();
        Query query = session.createQuery("from Enrollments where courseId = :courseId and lecturerId = :lecturerId");

        query.setInteger("lecturerId", lecturerId);
        query.setInteger("courseId", courseId);
        List<Enrollments> enrollList = new ArrayList<>();
        enrollList = query.list();

        return enrollList;

    }

    public Long counttEnrollmentsList(Integer courseId, Integer lecturerId) {

        this.findSession();
        return (Long) session.createCriteria(Enrollments.class)
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
