package com.zwq.parent.dto;

import java.io.Serializable;
import java.util.List;

/**
 * created by zwq on 2018/5/15
 */
public class OrderDataDTO implements Serializable {

    private int totalMoney;
    private List<TeaData> teaDatas;

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<TeaData> getTeaDatas() {
        return teaDatas;
    }

    public void setTeaDatas(List<TeaData> teaDatas) {
        this.teaDatas = teaDatas;
    }

    @Override
    public String toString() {
        return "{" +
                "totalMoney:" + totalMoney +
                ", teaDatas:" + teaDatas +
                '}';
    }
}
