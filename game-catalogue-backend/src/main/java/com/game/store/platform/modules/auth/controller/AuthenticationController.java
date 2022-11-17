package com.game.store.platform.modules.auth.controller;

import com.game.store.platform.modules.auth.model.UserCredentials;
import com.game.store.platform.modules.auth.service.AuthenticationService;
import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import java.util.Map;
import java.util.Map.Entry;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PreAuthorize("permitAll()")
  @PostMapping(value = "/login")
  public ResponseEntity<Map.Entry<String,String>> login(@RequestBody UserCredentials credentials)
      throws AuthenticationException, UserNotFoundException {
    Entry<String, String> token = Map.entry("token", authenticationService.login(credentials));
    return new ResponseEntity<>(token, HttpStatus.OK);
  }

  @PreAuthorize("permitAll()")
  @PostMapping(value = "/register")
  public ResponseEntity<User> register(@Valid @RequestBody RegisterUser userToRegister)
      throws UserExistsException {
    return new ResponseEntity<>(authenticationService.register(userToRegister), HttpStatus.OK);
  }
}
