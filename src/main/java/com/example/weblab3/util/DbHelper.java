package com.example.weblab3.util;

import com.example.weblab3.AttemptBean;
import com.example.weblab3.AttemptEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbHelper {
    static SessionFactory sessionFactory =
            new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AttemptEntity.class).buildSessionFactory();
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() {
        if (sessionFactory.isOpen()){
            sessionFactory.getCurrentSession().close();
        }
        else{
            System.out.println("DB session created");
        }
        return sessionFactory.openSession();
    }
}
