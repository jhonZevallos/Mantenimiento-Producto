package com.ntt.bc.service;

import java.util.List;

public interface ICRUD<T, V> {
    T crear(T obj);

    T actualizar(T obj);

    List<T> listarTodo();

    T buscarPorId(V id);

    void eliminar(V id);

}
