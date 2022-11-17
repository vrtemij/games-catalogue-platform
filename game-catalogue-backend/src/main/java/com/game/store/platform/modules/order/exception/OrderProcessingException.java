package com.game.store.platform.modules.order.exception;

public class OrderProcessingException extends Exception {

  public OrderProcessingException(String msg) {
    super(msg);
  }

  public OrderProcessingException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
