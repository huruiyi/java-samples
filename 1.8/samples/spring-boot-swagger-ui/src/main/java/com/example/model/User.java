package com.example.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "响应结果-用户信息")
public class User {

  @ApiModelProperty(value = "用户id")
  private Integer uid;
  @ApiModelProperty(value = "用户名")
  private String uname;
  @ApiModelProperty(value = "用户密码")
  private String upasswd;
}