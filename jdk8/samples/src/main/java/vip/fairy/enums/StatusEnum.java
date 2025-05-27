package vip.fairy.enums;

public enum StatusEnum {

  Draf("草稿"),
  Submit("提交审核"),
  Confirm("审核通过"),
  Refuse("拒绝"),
  Cancel("删除");

  public String status;

  StatusEnum(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
