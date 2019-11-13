/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import attendance.actors.Lecturers;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mofoluwaso
 */
public class LecturersModel extends AbstractModel<Lecturers> {

    StatelessSession session;

    public LecturersModel() {
        super(Lecturers.class);
    }

    public Lecturers findLecturer(String username, String password) {
        this.findSession();
        return (Lecturers) session.createCriteria(Lecturers.class)
                .add(Restrictions
                        .and(Restrictions.eq("email", username),
                                Restrictions.eq("password", password))).uniqueResult();

    }

    public Lecturers findByEmail(String email) {

        this.findSession();
        Query query = session.createQuery("from Lecturers where email = :email");

        query.setString("email", email);

        return (Lecturers) query.uniqueResult();

    }

    @Override
    public void findSession() {

        try {

            session = sessionFactory.openStatelessSession();

        } catch (Exception e) {
        }
    }
}
