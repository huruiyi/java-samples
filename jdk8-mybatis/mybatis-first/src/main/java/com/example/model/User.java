package com.example.model;


public class User {

  private Long id;

  private String userName;

  private Byte sex;

  private String mobile;

  private String userEmail;

  private String note;

  private Long positionId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Byte getSex() {
    return sex;
  }

  public void setSex(Byte sex) {
    this.sex = sex;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Long getPositionId() {
    return positionId;
  }

  public void setPositionId(Long positionId) {
    this.positionId = positionId;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", sex=" + sex +
        ", mobile='" + mobile + '\'' +
        ", userEmail='" + userEmail + '\'' +
        ", note='" + note + '\'' +
        ", positionId=" + positionId +
        '}';
  }
}
