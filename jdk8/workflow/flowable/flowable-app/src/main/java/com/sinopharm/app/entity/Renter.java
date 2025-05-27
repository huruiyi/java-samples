package com.sinopharm.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_renter")
public class Renter {

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private Long id;

  private String renterName;
}
