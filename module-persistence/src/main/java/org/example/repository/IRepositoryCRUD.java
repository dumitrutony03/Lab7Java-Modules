package org.example.repository;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface IRepositoryCRUD<ID, T>{
    void Add(T elem);
    void Delete(ID id) throws ExecutionControl.NotImplementedException;
    void Update(ID id, T elem) throws ExecutionControl.NotImplementedException;
    T FindById(ID id) throws ExecutionControl.NotImplementedException;
    List<T> FindAll();
}