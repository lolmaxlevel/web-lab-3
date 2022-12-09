package com.example.weblab3.util;


import com.example.weblab3.AttemptBean;
import com.example.weblab3.AttemptEntity;

import java.io.Serializable;


import java.util.Date;


public class AreaCheck implements Serializable {

    public static void checkHit(AttemptEntity attemptEntity) {
        long startTime = System.nanoTime();
        attemptEntity.setAttemptTime(new Date());
        boolean hit = attemptIsInArea(attemptEntity);
        attemptEntity.setHit(hit);
        attemptEntity.setProcessTime(System.nanoTime()-startTime);
    }

    private static boolean attemptIsInArea(AttemptEntity attemptEntity) {
        return attemptIsInRect(attemptEntity) || attemptIsInTriangle(attemptEntity) || attemptIsInCircle(attemptEntity);
    }

    private static boolean attemptIsInRect(AttemptEntity attemptEntity) {
        double x = attemptEntity.getX();
        double y = attemptEntity.getY();
        int r = attemptEntity.getR();

        return (x>=0 && y>=0 && x<=r/2.0 && y<=r);
    }

    private static boolean attemptIsInCircle(AttemptEntity attemptEntity) {
        double x = attemptEntity.getX();
        double y = attemptEntity.getY();
        int r = attemptEntity.getR();
        double a = 0 - (-r/2.0 - 0) * (0 - y);
        double b = (-r/2.0 - x) * (-r) - (0 - (-r/2.0)) * (0 - y);
        double c = (-x) * (-(-r)) - (0) * (-r - y);
        return ((a>=0 && b>=0 && c>=0) || (a<=0 && b<=0 && c<=0));
    }

    private static boolean attemptIsInTriangle(AttemptEntity attemptEntity) {
        double x = attemptEntity.getX();
        double y = attemptEntity.getY();
        int r = attemptEntity.getR();

        return (x >= 0 && y <= 0 && y >= x - r / 2.0);
    }
}