package com.game.store.platform.modules.user.controller;

import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PutMapping(value = "/update")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
    return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
  }
}
