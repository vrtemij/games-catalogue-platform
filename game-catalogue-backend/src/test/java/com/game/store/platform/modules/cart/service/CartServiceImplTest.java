package com.game.store.platform.modules.cart.service;

import static com.game.store.platform.modules.utils.TestUtil.GAME_PRICE;
import static com.game.store.platform.modules.utils.TestUtil.SESSION_ID;
import static com.game.store.platform.modules.utils.TestUtil.createCart;
import static com.game.store.platform.modules.utils.TestUtil.createGame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.model.entity.CartEntity;
import com.game.store.platform.modules.cart.repository.CartRepository;
import com.game.store.platform.modules.cart.service.mapper.CartMapper;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.service.GameService;
import java.util.Optional;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {
  
  @InjectMocks
  public CartServiceImpl cartService;

  @Mock
  public GameService gameService;

  @Mock
  public CartRepository cartRepository;

  @Spy
  public CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(cartService, "cartMapper", cartMapper);
  }

  @Test
  public void testGetUserCart_whenEverythingIsOk_thenSuccess() throws CartNotFoundException {
    Set<Game> games = Set.of(
        createGame(1L, "SUPER GAME", GAME_PRICE),
        createGame(2L, "SUPER GAME PART 2", GAME_PRICE)
    );
    Cart cart = createCart(1L, SESSION_ID, games);
    CartEntity cartEntity = cartMapper.toEntity(cart);

    Mockito.when(cartRepository.findBySessionId(anyString())).thenReturn(Optional.of(cartEntity));

    Cart actualCart = cartService.getUserCart(SESSION_ID);
    Assert.assertNotNull(actualCart);
  }

  @Test
  public void testGetUserCart_whenNoSessionId_thenThrowException() {
    Set<Game> games = Set.of(
        createGame(1L, "SUPER GAME", GAME_PRICE),
        createGame(2L, "SUPER GAME PART 2", GAME_PRICE)
    );
    Cart cart = createCart(1L, SESSION_ID, games);
    CartEntity cartEntity = cartMapper.toEntity(cart);

    Mockito.when(cartRepository.findBySessionId(anyString())).thenReturn(Optional.empty());

    Assert.assertThrows(CartNotFoundException.class, () -> cartService.getUserCart(SESSION_ID));
  }

  @Test
  public void testAddToCart_whenEverythingIsOk_thenSuccess() throws GameNotFoundException {
    Game game = createGame(1L, "SUPER GAME", GAME_PRICE);
    Game gameToAdd = createGame(2L, "SUPER GAME PART 2", GAME_PRICE);

    Cart cart = createCart(1L, SESSION_ID, Set.of(game));
    Cart updatedCart = createCart(1L, SESSION_ID, Set.of(game, gameToAdd));

    CartEntity cartEntity = cartMapper.toEntity(cart);
    CartEntity updatedCartEntity = cartMapper.toEntity(updatedCart);

    Mockito.when(gameService.findGameById(anyLong())).thenReturn(game);
    Mockito.when(cartRepository.findBySessionId(anyString())).thenReturn(Optional.of(cartEntity));
    Mockito.when(cartRepository.save(any())).thenReturn(updatedCartEntity);

    Cart actualCart = cartService.addToCart(gameToAdd.getId(), SESSION_ID);
    Assert.assertEquals(2, actualCart.getGames().size());
  }

  @Test
  public void testAddToCart_whenGameNotFound_thenThrow() throws GameNotFoundException {
    Game gameToAdd = createGame(2L, "SUPER GAME PART 2", GAME_PRICE);

    Mockito.when(gameService.findGameById(anyLong())).thenThrow(GameNotFoundException.class);

    Assert.assertThrows(GameNotFoundException.class, () -> cartService.addToCart(gameToAdd.getId(), SESSION_ID));
  }

  @Test
  public void testAddToCart_whenSessionNotFound_thenReturnNewCart() throws GameNotFoundException {
    Game game = createGame(1L, "SUPER GAME", GAME_PRICE);
    Cart cart = createCart(1L, SESSION_ID, Set.of(game));
    CartEntity cartEntity = cartMapper.toEntity(cart);

    Mockito.when(gameService.findGameById(anyLong())).thenReturn(game);
    Mockito.when(cartRepository.findBySessionId(anyString())).thenReturn(Optional.empty());
    Mockito.when(cartRepository.save(any())).thenReturn(cartEntity);

    Cart actualCart = cartService.addToCart(game.getId(), SESSION_ID);
    Assert.assertNotNull(cart);
    Assert.assertEquals(1, actualCart.getGames().size());
  }

}