package com.bootcampmanagement.server.basic.models;

import java.util.List;
import lombok.Data;

@Data
public class AuthenticationToken {

    private String username;
    private String[] authorities;

    public AuthenticationToken() {
        super();
    }

    public AuthenticationToken(String username, List<String> authorities) {
        this.authorities= authorities.toArray(new String[authorities.size()]);
        this.username = username;
    }

}