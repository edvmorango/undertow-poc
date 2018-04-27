package com.persistence.repository;

import java.util.List;

public interface GenericRepository<T> {

    T create(T obj);

    T findById(String uid);

    List<T> list();

}
