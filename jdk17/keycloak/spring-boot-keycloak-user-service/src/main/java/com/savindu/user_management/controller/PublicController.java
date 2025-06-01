package com.savindu.user_management.controller;


import com.savindu.user_management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

  private final UserService userService;

  @PutMapping("/{username}/forgot-password")
  public void updatePassword(@PathVariable String username) {
    userService.forgotPassword(username);
  }

}
