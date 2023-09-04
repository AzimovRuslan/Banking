package org.clevertec.service;

import java.io.IOException;

public interface IService<T> {
    void save(T t) throws IOException;
}