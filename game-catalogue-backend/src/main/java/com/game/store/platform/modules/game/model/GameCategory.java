package com.game.store.platform.modules.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameCategory {

  ACTION("Action"),
  STRATEGY("Strategy"),
  ADVENTURE("Adventure"),
  SPORT("Sport"),
  RACING("Racing");

  private final String value;

}
