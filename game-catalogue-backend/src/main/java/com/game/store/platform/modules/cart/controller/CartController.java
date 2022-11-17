package com.game.store.platform.modules.cart.controller;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.service.CartService;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.GameId;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<Cart> getUserCart(HttpSession session) throws CartNotFoundException {
    return new ResponseEntity<>(cartService.getUserCart(session.getId()), HttpStatus.OK);
  }

  @PutMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<Cart> addToCart(@Valid @RequestBody GameId gameId,  HttpSession session)
      throws GameNotFoundException {
    return new ResponseEntity<>(cartService.addToCart(gameId.getId(), session.getId()), HttpStatus.OK);
  }

  @DeleteMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<Object> deleteFromCart(@Valid @RequestBody GameId gameId,  HttpSession session)
      throws GameNotFoundException, CartNotFoundException {
    cartService.deleteFromCart(gameId.getId(), session.getId());
    return ResponseEntity.ok().build();
  }
}
