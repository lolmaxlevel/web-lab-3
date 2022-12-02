package com.example.weblab3;

import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named("attemptsRepository")
@ApplicationScoped
public class HibernateWorker implements Serializable {

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AttemptBean.class).buildSessionFactory();

    {
        System.out.println("DbManager created");
    }

    public List<AttemptBean> getAttemptsList() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<AttemptBean> data = session.createQuery("From AttemptBean ").list();
        session.getTransaction().commit();
        return data;
    }

    public void addAttempt(AttemptBean attemptBean) {
        attemptBean.setHit(true);
        System.out.println("addAttempt");
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(attemptBean);
        session.getTransaction().commit();
    }

    public void clearAttempts() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String hql = String.format("delete from %s", AttemptBean.class.getName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        }

    }

    public void addAttemptFromJsParams(int currentR) {
        System.out.println("addAttemptFromJsParams");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            double xCoordinate = Double.parseDouble(params.get("x"));
            double yCoordinate = Double.parseDouble(params.get("y"));
            double graphR = Double.parseDouble(params.get("r"));
            final AttemptBean attemptBean = new AttemptBean(
                    xCoordinate / graphR * currentR,
                    yCoordinate / graphR * currentR,
                    currentR
            );
            addAttempt(attemptBean);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}