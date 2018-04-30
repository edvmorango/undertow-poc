package com.persistence.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

    T create(T obj) throws Exception;

    Optional<T> findById(String uid);

    List<T> list();

}
