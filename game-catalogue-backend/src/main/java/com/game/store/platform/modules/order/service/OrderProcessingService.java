package com.game.store.platform.modules.order.service;

import com.game.store.platform.modules.order.exception.OrderProcessingException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import java.util.Map;

public interface OrderProcessingService {

  void placeOrderForRegisteredUser(String sessionId, String username) throws UserNotFoundException, OrderProcessingException;

  Map<String,String> placeOrderForAnonymousUser(String sessionId)
      throws OrderProcessingException;
}
