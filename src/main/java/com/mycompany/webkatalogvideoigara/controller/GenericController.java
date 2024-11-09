/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara.controller;

/**
 *
 * @author uros
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mycompany.webkatalogvideoigara.model.GenericConverter;
import com.mycompany.webkatalogvideoigara.service.GenericService;







public abstract class GenericController<T, DTO, S extends GenericService<T, Integer>> {

    protected S service;

    public GenericController(S service) {
		this.service=service;
	}

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable Integer id) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        @SuppressWarnings("unchecked")
        Class<DTO> dtoClass = (Class<DTO>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];

        T entity = service.findById(id).get();

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        GenericConverter<T, DTO> genConv = new GenericConverter<>();
        DTO dto = genConv.convertToDTO(entity, dtoClass);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<Iterable<DTO>> findAll() throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("unchecked")
		Class<DTO> dtoClass = (Class<DTO>) ((ParameterizedType) getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[1];

        ArrayList<DTO> dtos = new ArrayList<>();
        Iterable<T> entiteti = service.findAll();

        for (T entitet : entiteti) {
            GenericConverter<T, DTO> genConv = new GenericConverter<>();
            DTO dto = genConv.convertToDTO(entitet, dtoClass);
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<DTO> add(@RequestBody DTO dto) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	    @SuppressWarnings("unchecked")   
	    Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
	    GenericConverter<T, DTO> genConv = new GenericConverter<>();
	    T entity = GenericConverter.staticConvertToEntity(dto, entityClass);
//	    return new ResponseEntity(entity, HttpStatus.CREATED);
	    T savedEntity = service.save(entity);
	    @SuppressWarnings("unchecked")
		DTO savedDto = genConv.convertToDTO(savedEntity, (Class<DTO>) dto.getClass());
	    return new ResponseEntity<DTO>(savedDto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DTO> update(@PathVariable("id") Long id, @RequestBody DTO dto) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	    @SuppressWarnings("unchecked")
	    Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
	    GenericConverter<T, DTO> genConv = new GenericConverter<>();
	    T entityToUpdate = genConv.convertToEntity(dto, entityClass);
	    T updatedEntity = service.save(entityToUpdate);
	    @SuppressWarnings("unchecked")
	    DTO updatedDto = genConv.convertToDTO(updatedEntity, (Class<DTO>) dto.getClass());
	    return new ResponseEntity<>(updatedDto, HttpStatus.OK);
	}


}
