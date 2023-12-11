package com.example.biyeboot.entity;


import java.io.Serializable;

/**
 * json格式进行响应
 */
public class JsonResult<E> implements Serializable {
    /***/
    private Integer state;
    private String message;
    private E data;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public JsonResult(Integer state){
        this.state=state;
    }
    public JsonResult(Integer state, E data){
        this.state=state;
        this.data=data;
    }
    public JsonResult(Integer state,String message){
        this.state=state;
        this.message=message;
    }

    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }
    public JsonResult(){

    }
}
