package com.game.store.platform.modules.cart.model;

import com.game.store.platform.modules.game.model.Game;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Cart {

  private Long id;

  private String sessionId;

  private Set<Game> games;

}
