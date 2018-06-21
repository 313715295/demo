package com.zwq.parent.dto;



import com.zwq.commons.enums.MyEnum;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/23
 */
public class Result<T> implements Serializable{

    private boolean result;
    private int state;
    private String stateInfo;
    private T data;



    public Result(boolean result, String stateInfo, T data) {
        this.result = result;
        this.stateInfo = stateInfo;
        this.data = data;
    }

    public Result(boolean result, MyEnum myEnum, T data) {
        this.result = result;
        this.state = myEnum.getState();
        this.stateInfo = myEnum.getStateInfo();
        this.data = data;
    }




    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T date) {
        this.data = data;
    }
}
