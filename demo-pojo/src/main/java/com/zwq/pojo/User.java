package com.zwq.pojo;

import java.io.Serializable;

/**
 * created by zwq on 2018/5/10
 */
public class User implements Serializable{

    private Integer id;
    private String name;
    private String password;
    private Integer autho = 1;


    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:" + name +
                ", password:" + password +
                ", autho:" + autho +
                '}';
    }

    public Integer getAutho() {
        return autho;
    }

    public void setAutho(Integer autho) {
        this.autho = autho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
