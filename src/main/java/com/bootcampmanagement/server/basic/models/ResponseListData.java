package com.bootcampmanagement.server.basic.models;

import java.util.List;
import lombok.Getter;

@Getter
public class ResponseListData <E> {
    private List<E> data;

    public ResponseListData(List<E> data) {
        this.data = data;
    }
}
