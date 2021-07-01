package com.bootcampmanagement.clientapp.models;

public class Sent {
    private Integer sent;
    private Integer need;

    public Sent() {
        
    }

    public Sent(Integer sent, Integer need) {
        this.sent = sent;
        this.need = need;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }
    
}
