package com.bootcampmanagement.server.basic.models;

public class ResponseData <E> {
    
    private E data;

    public ResponseData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}