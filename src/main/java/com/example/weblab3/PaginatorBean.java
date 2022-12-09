package com.example.weblab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Named("paginatorBean")
@SessionScoped
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PaginatorBean implements Serializable {
    private int from = 0;
    private int count = 10;
}
