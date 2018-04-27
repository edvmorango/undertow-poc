package com.service;

import java.util.List;

public interface GenericService<T> {

    T create(T obj);

    T findById(String uid);

    List<T> list();
}
