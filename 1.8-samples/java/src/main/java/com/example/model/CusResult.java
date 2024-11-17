package com.example.model;

import java.util.List;

public class CusResult {

  private Integer status;
  private String msg;
  private List<Object> data;

  public CusResult(Integer status, String msg, List<Object> data) {
    this.status = status;
    this.msg = msg;
    this.data = data;
  }

  public static CusResult OK(String msg, List<Object> data) {
    return new CusResult(200, msg, data);
  }

  public static CusResult Error(Integer status, String msg) {
    return new CusResult(status, msg, null);
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

  public List<Object> getData() {
    return data;
  }

  public void setData(List<Object> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "CusResult{" +
        "status=" + status +
        ", msg='" + msg + '\'' +
        ", data=" + data +
        '}';
  }
}
