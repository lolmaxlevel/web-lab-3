package com.example.weblab3;

import com.example.weblab3.util.DbHelper;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named("attemptsRepository")
public class HibernateWorker implements Serializable {

    public List<AttemptBean> getAttemptsList() {
        Session session = DbHelper.getSession();
        session.beginTransaction();
        List<AttemptBean> data = session.createQuery("From AttemptBean ").list();
        session.getTransaction().commit();
        return data;
    }

    public void addAttempt(AttemptBean attemptBean) {
        attemptBean.setHit(true);
        System.out.println("addAttempt");
        Session session = DbHelper.getSession();
        session.beginTransaction();
        session.save(attemptBean);
        session.getTransaction().commit();
    }

    public void clearAttempts() {
        Session session = DbHelper.getSession();
        session.beginTransaction();
        System.out.println("clearing table");
        String hql = String.format("delete from %s", AttemptBean.class.getName());
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();

    }

    public void addAttemptFromJsParams(int currentR) {
        System.out.println("addAttemptFromJsParams");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            double xCoordinate = Double.parseDouble(params.get("x").substring(0,4));
            double yCoordinate = Double.parseDouble(params.get("y").substring(0,4));
            int graphR = Integer.parseInt(params.get("r"));
            final AttemptBean attemptBean = new AttemptBean(
                    xCoordinate,
                    yCoordinate,
                    graphR
            );
            addAttempt(attemptBean);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}