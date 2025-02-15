package vip.fairy.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "菜单实体")
public class Menu {

  @ApiModelProperty(value = "主键")
  private int id;

  @ApiModelProperty(value = "菜单名称")
  private String name;

}
