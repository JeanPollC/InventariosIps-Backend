package com.inventariosips.service.impl;

import com.inventariosips.repo.IGenericRepo;
import com.inventariosips.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        getRepo().findById(id);
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow();
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow();
        getRepo().deleteById(id);
    }
}
