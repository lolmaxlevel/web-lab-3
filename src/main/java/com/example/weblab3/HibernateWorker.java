package com.example.weblab3;

import com.example.weblab3.util.AreaCheck;
import com.example.weblab3.util.DbHelper;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named("attemptsRepository")
public class HibernateWorker implements Serializable {
    @PersistenceContext
    EntityManager entityManager;

    public List<AttemptEntity> getAttemptsList() {
        Session session = DbHelper.getSession();
        session.beginTransaction();
        List<AttemptEntity> data = session.createQuery("From AttemptEntity ").list();
        session.getTransaction().commit();
        return data;
    }

    public void addAttempt(AttemptBean attemptBean) {
        AttemptEntity attemptEntity = attemptBean.createEntity();
        AreaCheck.checkHit(attemptEntity);
        System.out.println("addAttempt"+attemptEntity);
        Session session = DbHelper.getSession();
        session.beginTransaction();
        session.save(attemptEntity);
        session.getTransaction().commit();
    }

    public void clearAttempts() {
        Session session = DbHelper.getSession();
        session.beginTransaction();
        System.out.println("clearing table");
        String hql = String.format("delete from %s", AttemptEntity.class.getName());
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();

    }

    public void addAttemptFromJsParams(int currentR) {
        System.out.println("addAttemptFromJsParams");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            double xCoordinate = Double.parseDouble(params.get("x"));
            double yCoordinate = Double.parseDouble(params.get("y"));
            xCoordinate = Math.round(xCoordinate * 100)/100.0;
            yCoordinate = Math.round(yCoordinate * 100)/100.0;
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