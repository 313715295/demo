package com.zwq.commons.enums;

/**
 * created by zwq on 2018/5/25
 */
public enum ExceptionEnum implements MyEnum  {


    STOCKOUT(-1, "库存不足"),
    INNER_ERROR(-2, "系统异常"),
    PRODUCT_REPEAT(-3, "商品重复"),
    LOGIN_TIMEOUT(-4, "登录超时"),
    DATA_FAILURE(-5, "数据失效"),
    DELETE_FAIL(-6, "删除失败"),
    LOGIN_FAIL(-7, "账号或密码错误"),
    USERNAME_REPEAT(-8, "账号已存在"),
    EDITOR_PASSWORD_FAIL(-9, "修改密码失败");




    private int state;
    private String stateInfo;

    ExceptionEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    @Override
    public int getState() {
        return state;
    }
    @Override
    public String getStateInfo() {
        return stateInfo;
    }





}
