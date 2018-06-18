package com.zwq.parent.dto;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/15
 */
public class TeaData implements Serializable{

    private int tid;
    private int count;



    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "tid:" + tid +
                ", count:" + count +
                '}';
    }
}
