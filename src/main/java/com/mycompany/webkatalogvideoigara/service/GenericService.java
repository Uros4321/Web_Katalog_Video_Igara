/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara.service;

/**
 *
 * @author uros
 */
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericService<T, ID> {
    
    protected JpaRepository<T, ID> repository;
    
    public GenericService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }
    
    public Iterable<T> findAll() {
        return repository.findAll();
    }
    
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }
    
    public void delete(T entity) {
        repository.delete(entity);
    }
    
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    public Iterable<T> sortBasedOnField(String field, String directionString) {
        return repository.findAll(Sort.by(Sort.Direction.fromString(directionString), field));
    }
    
    public Iterable<T> findAllWithPagination(int pageNum, int pageSize) {
        return repository.findAll(PageRequest.of(pageNum, pageSize));
    }
    
    public Iterable<T> sortAndPaginate(int pageNum, int pageSize, String field, String direction) {
        return repository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.fromString(direction), field)));
    }
//    
//    public Iterable<T> filterAll(String[] fields, Object[] criteria){
//        return repository.findAll(example)
//    }
    
}
