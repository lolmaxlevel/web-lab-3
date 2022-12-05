package com.example.weblab3.util;


import com.example.weblab3.AttemptBean;
import jakarta.inject.Named;

import java.io.Serializable;


import java.util.Date;


@Named("areaCheck")
public class AreaCheck implements Serializable {

    public static void checkHit(AttemptBean attemptBean) {
        long startTime = System.nanoTime();
        attemptBean.setAttemptTime(new Date());
        boolean hit = attemptIsInArea(attemptBean);
        attemptBean.setHit(hit);
        attemptBean.setProcessTime(System.nanoTime()-startTime);
    }

    private static boolean attemptIsInArea(AttemptBean attemptBean) {
        return attemptIsInRect(attemptBean) || attemptIsInTriangle(attemptBean) || attemptIsInCircle(attemptBean);
    }

    private static boolean attemptIsInRect(AttemptBean attemptBean) {
        double x = attemptBean.getX();
        double y = attemptBean.getY();
        int r = attemptBean.getR();

        return (x>=0 && y>=0 && x<=r/2.0 && y<=r);
    }

    private static boolean attemptIsInCircle(AttemptBean attemptBean) {
        double x = attemptBean.getX();
        double y = attemptBean.getY();
        int r = attemptBean.getR();
        double a = 0 - (-r/2.0 - 0) * (0 - y);
        double b = (-r/2.0 - x) * (-r) - (0 - (-r/2.0)) * (0 - y);
        double c = (-x) * (-(-r)) - (0) * (-r - y);
        return ((a>=0 && b>=0 && c>=0) || (a<=0 && b<=0 && c<=0));
    }

    private static boolean attemptIsInTriangle(AttemptBean attemptBean) {
        double x = attemptBean.getX();
        double y = attemptBean.getY();
        int r = attemptBean.getR();

        return (x >= 0 && y <= 0 && y >= x - r / 2.0);
    }
}