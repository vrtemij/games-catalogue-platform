package com.game.store.platform.modules.user.exception;

public class UserNotFoundException extends Exception {
  public UserNotFoundException(String message, String... args) {
    super(String.format(message,args));
  }
}
