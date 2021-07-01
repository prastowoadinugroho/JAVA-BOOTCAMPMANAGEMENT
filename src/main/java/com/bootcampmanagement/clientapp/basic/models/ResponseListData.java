package com.bootcampmanagement.clientapp.basic.models;

import java.util.List;

public class ResponseListData <E> {
    private List<E> data;

    public ResponseListData() {
        
    }
    
    public ResponseListData(List<E> data) {
        this.data = data;
    }

    public List<E> getData() {
        return data;
    }
}
