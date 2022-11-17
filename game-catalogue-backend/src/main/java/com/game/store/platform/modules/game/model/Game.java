package com.game.store.platform.modules.game.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

  private Long id;

  @NotNull(message = "Game should have name")
  private String name;

  private String description;

  @NotNull(message = "Game should have category")
  private GameCategory category;

  @NotNull(message = "Game should have price")
  private String price;

}
