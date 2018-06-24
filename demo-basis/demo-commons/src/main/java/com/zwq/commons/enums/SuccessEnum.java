package com.zwq.commons.enums;

/**
 * created by zwq on 2018/5/23
 */
public enum SuccessEnum implements MyEnum {

    ADD_CARTITEM_SUCCESS(1, "成功加入购物车"),
    ADD_ORDER_SUCCESS(2, "成功提交订单"),
    PRODUCT_NAME_SUCCESS(3, null),
    PRODUCT_EDITOR_SUCCESS(4, "提交成功"),
    DELETE_SUCCESS(5, "删除成功"),
    LOGIN_SUCCESS(6, "登录成功"),
    REGISTER_SUCCESS(7, "注册成功"),
    EDITOR_PASSWORD_SUCCESS(8, "修改密码成功"),
    USERNAME_SUCCESS(9, "账号可以使用");



    private int state;
    private String stateInfo;

    SuccessEnum(int state, String stateInfo) {
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
