package com.example.weblab3;

import com.example.weblab3.util.AreaCheck;
import com.example.weblab3.util.DbHelper;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
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
        AreaCheck.checkHit(attemptBean);
        System.out.println("addAttempt"+attemptBean);
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