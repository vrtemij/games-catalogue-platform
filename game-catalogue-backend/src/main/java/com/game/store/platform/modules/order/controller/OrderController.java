package com.game.store.platform.modules.order.controller;

import com.game.store.platform.modules.order.exception.OrderProcessingException;
import com.game.store.platform.modules.order.service.OrderProcessingService;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("checkout")
@RequiredArgsConstructor
public class OrderController {

  private final OrderProcessingService orderProcessingService;

  @PostMapping
  public void purchase(HttpSession session)
      throws UserNotFoundException, OrderProcessingException {
    Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
    if (userAuth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
    ) {
      orderProcessingService.placeOrderForAnonymousUser(session.getId());
    } else {
      orderProcessingService.placeOrderForRegisteredUser(session.getId(), userAuth.getName());
    }
  }
}
