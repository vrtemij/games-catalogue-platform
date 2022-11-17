package com.game.store.platform.modules.utils;

import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.GameCategory;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.model.entity.RoleEnum;
import java.util.Set;

public class TestUtil {

  public static final String GAME_PRICE = "1";
  public static final String SESSION_ID = "sessionId";
  public static final String USERNAME = "user";

  public static Cart createCart(Long id, String sessionId, Set<Game> gameSet) {
    return Cart.builder()
        .id(id)
        .sessionId(sessionId)
        .games(gameSet)
        .build();
  }

  public static Game createGame(Long id, String name, String price) {
    return Game.builder()
        .id(id)
        .name(name)
        .category(GameCategory.ACTION)
        .price(price)
        .description("Description")
        .build();
  }

  public static User createUser(Long id, String username, Set<Game> gameSet) {
    return User.builder()
        .id(id)
        .username(username)
        .role(RoleEnum.REGISTERED_USER)
        .games(gameSet)
        .build();
  }
}
