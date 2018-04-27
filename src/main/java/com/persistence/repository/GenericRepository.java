package com.persistence.repository;

import java.util.List;

public interface GenericRepository<T> {

    public T create(T obj);

    public T find(String uid);

    public List<T> list();


}
