package com.example.weblab3;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
@Table(name = "attempts1")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class AttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attempt;
    @Column
    @NotNull
    private double x;
    @Column
    @NotNull
    @Max(5)
    @Min(-5)
    private double y;
    @Column
    @NotNull
    @Max(4)
    @Min(1)
    private int r = 1;
    @Column
    private boolean hit;
    @Column(name="process_time")
    @NotNull
    private Long processTime;
    @Column(name = "attempt_time")
    @NotNull
    @Past
    private Date attemptTime;
    @Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public AttemptEntity(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
