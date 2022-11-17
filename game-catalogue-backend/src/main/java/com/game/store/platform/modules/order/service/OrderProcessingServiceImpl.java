package com.game.store.platform.modules.order.service;

import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.service.CartService;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.order.exception.OrderProcessingException;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.service.UserService;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

  private final CartService cartService;

  private final UserService userService;

  @Override
  public void placeOrderForRegisteredUser(String sessionId, String username) throws OrderProcessingException {
    try {
      Cart cart = cartService.getUserCart(sessionId);
      User user = userService.findByUsername(username);

      if (!user.getGames().isEmpty()) {
        user.getGames().addAll(cart.getGames());
      }
      user.setGames(cart.getGames());
      cartService.deleteCart(cart);
      userService.update(user);
    } catch (Exception e) {
      throw new OrderProcessingException("Order processing failed", e);
    }
  }

  @Override
  public Map<String,String> placeOrderForAnonymousUser(String sessionId)
      throws OrderProcessingException {
    try {
      Cart cart = cartService.getUserCart(sessionId);
      Map<String, String> gameKeys = cart.getGames()
          .stream()
          .collect(Collectors.toMap(Game::getName, game -> generateKeyForGame()));
      cartService.deleteCart(cart);
      return gameKeys;
    } catch (Exception e) {
      throw new OrderProcessingException("Order processing failed", e);
    }
  }

  private String generateKeyForGame() {
    int leftLimit = 48;
    int rightLimit = 122;
    int targetStringLength = 10;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
