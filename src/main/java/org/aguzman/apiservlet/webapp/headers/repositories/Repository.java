package org.aguzman.apiservlet.webapp.headers.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    List<T> List() throws SQLException;
    T find(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
