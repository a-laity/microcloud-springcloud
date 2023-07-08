package com.imooc.grace.result;

/**
 * @author xujunchen
 * @date 2023/5/18 22:22
 * @describe todo
 */
public class GraceJSONResult {
    private Integer status;
    private String msg;
    private Boolean success;
    private Object data;

    public GraceJSONResult() {
    }

    public GraceJSONResult(Integer status, String msg, Boolean success, Object data) {
        this.status = status;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public GraceJSONResult(ResponseStatusEnum responseStatusEnum) {
        this.status = responseStatusEnum.status();
        this.msg = responseStatusEnum.msg();
        this.success = responseStatusEnum.success();
    }

    public GraceJSONResult(ResponseStatusEnum responseStatusEnum, Object data) {
        this.status = responseStatusEnum.status();
        this.msg = responseStatusEnum.msg();
        this.success = responseStatusEnum.success();
        this.data = data;
    }

    public GraceJSONResult(ResponseStatusEnum responseStatus, String msg) {
        this.status = responseStatus.status();
        this.msg = msg;
        this.success = responseStatus.success();
    }

    public GraceJSONResult(Object data) {
        this.status = ResponseStatusEnum.SUCCESS.status();
        this.msg = ResponseStatusEnum.SUCCESS.msg();
        this.success = ResponseStatusEnum.SUCCESS.success();
        this.data = data;
    }

    public static GraceJSONResult ok() {
        return new GraceJSONResult(ResponseStatusEnum.SUCCESS);
    }

    public static GraceJSONResult ok(Object data) {
        return new GraceJSONResult(data);
    }

    public static GraceJSONResult error() {
        return new GraceJSONResult(ResponseStatusEnum.FAILED);
    }

    public static GraceJSONResult errorMsg(String msg) {
        return new GraceJSONResult(ResponseStatusEnum.FAILED, msg);
    }

    public static GraceJSONResult exception(ResponseStatusEnum responseStatus) {
        return new GraceJSONResult(responseStatus);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
