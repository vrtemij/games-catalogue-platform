package com.game.store.platform.modules.cart.service;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.game.exception.GameNotFoundException;

public interface CartService {

  Cart getUserCart(String sessionId) throws CartNotFoundException;

  Cart addToCart(Long gameId, String sessionId) throws GameNotFoundException;

  void deleteFromCart(Long gameId, String sessionId)
      throws CartNotFoundException, GameNotFoundException;

  void deleteCart(Cart cart);
}
