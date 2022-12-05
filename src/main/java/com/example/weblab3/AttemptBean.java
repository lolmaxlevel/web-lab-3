package com.example.weblab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Named("attemptBean")
@SessionScoped
@Table(name = "attempts")
@Entity
@Getter
@Setter
public class AttemptBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attempt;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private int r = 1;
    @Column
    private boolean hit;
    @Column(name="process_time")
    private Long processTime;
    @Column(name = "attempt_time")
    private Date attemptTime;
    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public AttemptBean() {
    }

    public AttemptBean(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptBean that = (AttemptBean) o;
        return attempt == that.attempt && Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.r, r) == 0 && hit == that.hit && Objects.equals(processTime, that.processTime) && Objects.equals(attemptTime, that.attemptTime) && Objects.equals(simpleDateFormat, that.simpleDateFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, x, y, r, hit, processTime, attemptTime, simpleDateFormat);
    }

    @Override
    public String toString() {
        return "AttemptBean{" +
                "attempt=" + attempt +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", processTime=" + processTime +
                ", attemptTime=" + attemptTime +
                ", simpleDateFormat=" + simpleDateFormat +
                '}';
    }

    public Coordinates getCoordinates() {
        return new Coordinates(x, y, r, hit);
    }
    @Setter
    @Getter
    public static class Coordinates {
        private final double x;
        private final double y;
        private final int r;
        private final boolean hit;

        public Coordinates(double x, double y, int r, boolean hit) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.hit = hit;
        }

    }
}