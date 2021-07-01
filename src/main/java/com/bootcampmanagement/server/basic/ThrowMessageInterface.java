/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcampmanagement.server.basic;

import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author prastowoadi
 */
public interface ThrowMessageInterface {
    
    ResponseStatusException dataNotFound();
    
    ResponseStatusException dataAlreadyExist();
    
}
