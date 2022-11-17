package com.game.store.platform.modules.game.exception;

public class GameNotFoundException extends Exception {

  public GameNotFoundException(Long id) {
    super(String.format("Game with id %s was not found", id));
  }

}
