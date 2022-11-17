package com.game.store.platform.modules.game.exception;

public class GameExistsException extends Exception {

  public GameExistsException(String name) {
    super(String.format("The game with name %s already exists", name));
  }

}
