package com.example.web.user;

import com.example.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户操作")
@RequestMapping("user")
public class UserController {

  @PutMapping("user")
  @ApiOperation(value = "添加用户")
  @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
  public String addUser(@RequestBody User user) {
    return "Hello World";
  }

  @PostMapping("user")
  @ApiOperation(value = "根据id更新用户信息")
  @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
  public String updateUser(@RequestBody User user) {
    return "Hello World";
  }

  @DeleteMapping("user/{uid}")
  @ApiOperation(value = "根据id删除用户")
  @ApiImplicitParam(name = "uid", value = "用户id", dataType = "Integer")
  public String deleteUserById(@PathVariable Integer uid) {
    return "Hello World";
  }

  @ApiOperation(value = "根据用户id查询用户记录")
  @ApiImplicitParam(name = "uid", value = "查询参数", required = true)
  @GetMapping("user/{uid}")
  public String findUserById(@PathVariable Integer uid) {
    return "Hello World";
  }
}
