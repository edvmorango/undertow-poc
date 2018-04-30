package com.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    T create(T obj) throws Exception;

    Optional<T> findById(String uid);

    List<T> list();
}
