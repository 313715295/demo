package com.zwq.parent.enums;

/**
 * created by zwq on 2018/5/23
 */
public enum CartEnum  {
    SUCCESS(1, "成功加入购物车"),
    STOCKOUT(-1, "库存不足"),
    INNER_ERROR(-2, "系统异常");


    private int state;
    private String stateInfo;

    CartEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static CartEnum stateOf(int index) {
        for (CartEnum cartEnum:values()) {
            if (cartEnum.getState() == index) {
                return cartEnum;
            }
        }
        return null;
    }
}
