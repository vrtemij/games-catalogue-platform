package com.game.store.platform.modules.cart.service;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.model.entity.CartEntity;
import com.game.store.platform.modules.cart.repository.CartRepository;
import com.game.store.platform.modules.cart.service.mapper.CartMapper;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import com.game.store.platform.modules.game.service.GameService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  public final GameService gameService;

  public final CartRepository cartRepository;

  public final CartMapper cartMapper;

  @Override
  public Cart getUserCart(String sessionId) throws CartNotFoundException {
    CartEntity cartEntity = cartRepository.findBySessionId(sessionId)
        .orElseThrow(() -> new CartNotFoundException("Cart is empty"));
    return cartMapper.fromEntity(cartEntity);
  }

  @Override
  @Transactional
  public Cart addToCart(Long gameId, String sessionId) throws GameNotFoundException {
    Game game = gameService.findGameById(gameId);
    Cart cart = cartRepository.findBySessionId(sessionId)
        .map(cartMapper::fromEntity)
        .orElseGet(() -> Cart.builder()
                .sessionId(sessionId)
                .games(new HashSet<>(List.of(game)))
                .build()
        );
    cart.getGames().add(game);
    CartEntity saved = cartRepository.save(cartMapper.toEntity(cart));
    return cartMapper.fromEntity(saved);
  }

  @Override
  @Transactional
  public void deleteFromCart(Long gameId, String sessionId)
      throws CartNotFoundException, GameNotFoundException {
    Game game = gameService.findGameById(gameId);
    CartEntity cartEntity = cartRepository.findBySessionId(sessionId)
        .orElseThrow(() -> new CartNotFoundException("Cart is empty"));
    Set<GameEntity> games = cartEntity.getGames()
        .stream()
        .filter(gameEntity -> !gameEntity.getId().equals(game.getId()))
        .collect(Collectors.toSet());
    cartEntity.setGames(games);
    cartRepository.save(cartEntity);
  }

  @Override
  public void deleteCart(Cart cart) {
      cartRepository.deleteById(cart.getId());
  }
}
