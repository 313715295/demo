package com.zwq.parent.domain;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/6
 */
public class OrderItem implements Serializable {

    private Integer id;
    private Tea tea;
    private Integer count;
    private int oid;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tea getTea() {
        return tea;
    }

    public void setTea(Tea tea) {
        this.tea = tea;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", tea:" + tea +
                ", count:" + count +
                ", oid:" + oid +
                '}';
    }
}
