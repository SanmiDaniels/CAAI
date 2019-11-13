/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import java.io.*;
import org.hibernate.*;
import java.util.*;
import org.hibernate.context.internal.ManagedSessionContext;

/**
 *
 * @author MoFoLuWaSo
 * @param <T>
 */
//@SuppressWarnings("unchecked")
public class AbstractModel<T> {

    private Session session;
    private Class<T> entityClass;
    protected final SessionFactory sessionFactory = HibernateUtil.getInstance()
            .getSessionFactory();

    public AbstractModel(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractModel() {
    }

    public List<T> findAll() {
       // this.checkSession();
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }

            return sessionFactory.getCurrentSession()
                    .createQuery("from " + entityClass.getName()).list();
        } catch (RuntimeException re) {
            return null;
        }
    }

    public void update(T instance) {
        //this.checkSession();
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().merge(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
           // this.findSession();
    }

    public void delete(T instance) {
        //this.checkSession();
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().delete(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
       //  this.findSession();
    }

    public void create(T instance) {
        //this.checkSession();
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            sessionFactory.getCurrentSession().persist(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw re;
        }
    }

    public T find(Object primarykey) {
       // this.checkSession();
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            return (T) sessionFactory.getCurrentSession().get(entityClass,
                    (Serializable) primarykey);
        } catch (RuntimeException re) {
            return null;
        }
    }

    public void findSession() {

         if (!sessionFactory.getCurrentSession().isOpen()) {
            try {

                sessionFactory.openSession();
            } catch (Exception e) {
            }

        } 
    }

    

    public void checkSession() {
        if (!sessionFactory.getCurrentSession().isOpen()) {
            try {

                sessionFactory.openSession();
            } catch (Exception e) {
            }

        }

    }

}
