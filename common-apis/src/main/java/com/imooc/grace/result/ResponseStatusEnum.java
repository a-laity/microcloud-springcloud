package com.imooc.grace.result;

public enum ResponseStatusEnum {
    SUCCESS(200, true, "操作成功！"),
    SYSTEM_ERROR(555, false, "系统繁忙，请稍后再试！"),
    FAILED(500, false, "操作失败！");

    // 响应业务状态
    private Integer status;
    // 调用是否成功
    private Boolean success;
    // 响应消息，可以为成功或者失败的消息
    private String msg;

    public Integer status() {
        return status;
    }

    public Boolean success() {
        return success;
    }

    public String msg() {
        return msg;
    }

    ResponseStatusEnum(Integer status, Boolean success, String msg) {
        this.status = status;
        this.success = success;
        this.msg = msg;
    }
}
