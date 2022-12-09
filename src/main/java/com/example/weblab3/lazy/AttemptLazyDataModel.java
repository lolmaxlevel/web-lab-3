package com.example.weblab3.lazy;

import com.example.weblab3.AttemptEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

@Named("attemptsList")
@SessionScoped
public class AttemptLazyDataModel extends LazyDataModel<AttemptEntity> {

    @Inject
    private HibernateWorkerWithPagination service;

    public void setService(HibernateWorkerWithPagination service) {
        this.service = service;
    }

    @Override
    public int count(Map<String, FilterMeta> map) {
        System.out.println("Using count");
        return service.getAttemptsCount();
    }

    @Override
    public List<AttemptEntity> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
        System.out.println("Using load");
        return service.getAttemptsList(i, i1);
    }


}