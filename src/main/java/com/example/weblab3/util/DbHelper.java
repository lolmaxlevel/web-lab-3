package com.example.weblab3.util;

import com.example.weblab3.AttemptBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbHelper {
    static SessionFactory sessionFactory =
            new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AttemptBean.class).buildSessionFactory();
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() {
        if (sessionFactory.isOpen())

            return sessionFactory.getCurrentSession();
        else{
            System.out.println("DB session created");
            return sessionFactory.openSession();
        }
    }
}
