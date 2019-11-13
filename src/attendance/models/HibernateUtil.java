/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.models;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author mofoluwaso
 */
public class HibernateUtil {

    private static HibernateUtil instance = new HibernateUtil();
    
    private SessionFactory sessionFactory;
    
    private HibernateUtil(){
        this.sessionFactory = buildSessionFactory();
    }
    
    private synchronized static SessionFactory buildSessionFactory(){
        try{
            
        Configuration configuration = new Configuration();
        return configuration.configure()
                .buildSessionFactory(new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build());
    }catch(Exception er){
        er.printStackTrace();
        throw new RuntimeException();
    }
    }
    
    public static HibernateUtil getInstance(){
        if(instance==null){
            return new HibernateUtil();
        }
        return instance;
    }
    public SessionFactory getSessionFactory(){
        
        return sessionFactory;
    }
}