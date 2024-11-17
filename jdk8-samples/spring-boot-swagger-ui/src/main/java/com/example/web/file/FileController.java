package com.example.web.file;

import com.example.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "文件操作")
@RequestMapping("file")
public class FileController {

  @PutMapping("user")
  @ApiOperation(value = "上传文件")
  @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
  public String addUser(@RequestBody User user) {
    return "Hello World";
  }

  @PostMapping("user")
  @ApiOperation(value = "查询文件")
  @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
  public String updateUser(@RequestBody User user) {
    return "Hello World";
  }


}
