/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcampmanagement.server.basic.models;

import lombok.Data;

/**
 *
 * @author prastowoadi
 */
@Data
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}