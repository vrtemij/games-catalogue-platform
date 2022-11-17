package com.game.store.platform.modules.user.model;

import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.user.model.entity.RoleEnum;
import java.util.Set;
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
public class User {

  private Long id;

  private String username;

  private RoleEnum role;

  private Set<Game> games;
}
