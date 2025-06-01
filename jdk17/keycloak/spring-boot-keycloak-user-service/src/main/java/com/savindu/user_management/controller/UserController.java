package com.savindu.user_management.controller;

import com.savindu.user_management.response.UserRegistrationRecord;
import com.savindu.user_management.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@SecurityRequirement(name = "keycloak")
public class UserController {

  private final UserService userService;

  @PostMapping("/create-user")
  public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {
    return userService.createUser(userRegistrationRecord);
  }

  @GetMapping("/get-user")
  public UserRepresentation getUser(
      @RequestParam(name = "userId") String userId,
      @RequestParam(name = "userName") String userName) {
    return userService.getUserByIdOrName(userId, userName);
  }

  @DeleteMapping("/{userId}")
  public void deleteUserById(@PathVariable String userId) {
    userService.deleteUserById(userId);
  }


  @PutMapping("/{userId}/send-verify-email")
  public void sendVerificationEmail(@PathVariable String userId) {
    userService.emailVerification(userId);
  }

  @PutMapping("/update-password")
  public void updatePassword(Principal principal) {
    userService.updatePassword(principal.getName());
  }
}

