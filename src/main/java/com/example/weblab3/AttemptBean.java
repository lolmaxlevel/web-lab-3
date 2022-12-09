package com.example.weblab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.*;

import java.io.Serializable;
@Named("attemptBean")
@SessionScoped
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AttemptBean implements Serializable {
    private double x = 0;
    private double y = 0;
    private int r = 1;

    public AttemptEntity createEntity(){
        return new AttemptEntity(this.x, this.y, this.r);
    }
}
