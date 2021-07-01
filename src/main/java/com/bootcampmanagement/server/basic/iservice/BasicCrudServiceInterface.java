/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcampmanagement.server.basic.iservice;

import java.util.List;

/**
 *
 * @author prastowoadi
 */
public interface BasicCrudServiceInterface<E, I> {
        /**
     * get all data from table
     * @return list of data
     */
    List<E> findAll();
    
    /**
     * get data by id
     * @param id parameter of id
     * @return entity data
     */
    E findById(I id);
    
    /**
     * create new data
     * @param data data will be save
     * @return entity data
     */
    E create(E data);
    
    /**
     * Update data
     * @param data data will be update
     * @param id id data
     * @return entity data
     */
    E update(E data, I id);
    
    /**
     * Delete data 
     * @param id id will be deleted
     * @return entity data
     */
    E delete(I id);
    
}