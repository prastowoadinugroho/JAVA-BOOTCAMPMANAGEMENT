package com.bootcampmanagement.clientapp.basic.models;

public class ResponseData <E> {
    
    private E data;

    public ResponseData() {
        
    }
    
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