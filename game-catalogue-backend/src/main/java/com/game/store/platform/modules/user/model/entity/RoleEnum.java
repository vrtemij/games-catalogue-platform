package com.game.store.platform.modules.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

  ADMIN("ADMIN"),
  REGISTERED_USER("REGISTERED_USER");

  private final String value;

}