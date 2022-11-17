package com.game.store.platform.modules.cart.exception;

public class CartNotFoundException extends Exception {

  public CartNotFoundException(String message, Object... args) {
    super(String.format(message, args));
  }

}