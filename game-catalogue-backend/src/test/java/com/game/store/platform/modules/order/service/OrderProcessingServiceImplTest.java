package com.game.store.platform.modules.order.service;

import static com.game.store.platform.modules.utils.TestUtil.GAME_PRICE;
import static com.game.store.platform.modules.utils.TestUtil.SESSION_ID;
import static com.game.store.platform.modules.utils.TestUtil.USERNAME;
import static com.game.store.platform.modules.utils.TestUtil.createCart;
import static com.game.store.platform.modules.utils.TestUtil.createGame;
import static com.game.store.platform.modules.utils.TestUtil.createUser;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.service.CartService;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.order.exception.OrderProcessingException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.service.UserService;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderProcessingServiceImplTest {

  @InjectMocks
  private OrderProcessingServiceImpl orderProcessingService;

  @Mock
  private CartService cartService;

  @Mock
  private UserService userService;

  @Test
  public void testPlaceOrderForRegisteredUser_whenEverythingIsOk_thenSuccess()
      throws CartNotFoundException, UserNotFoundException, OrderProcessingException {
    Set<Game> games = Set.of(
        createGame(1L, "SUPER GAME", GAME_PRICE),
        createGame(2L, "SUPER GAME PART 2", GAME_PRICE)
    );
    Cart cart = createCart(1L, SESSION_ID, games);
    User user = createUser(1L, USERNAME, Set.of());
    User updatedUser = createUser(1L, USERNAME, games);

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    Mockito.when(cartService.getUserCart(Mockito.anyString())).thenReturn(cart);
    Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user);

    orderProcessingService.placeOrderForRegisteredUser(SESSION_ID, USERNAME);

    Mockito.verify(userService, Mockito.times(1)).update(userArgumentCaptor.capture());

    User actualUpdatedUser = userArgumentCaptor.getValue();
    Assert.assertEquals(updatedUser.getGames(), actualUpdatedUser.getGames());
  }

  @Test
  public void testPlaceOrderForRegisteredUser_whenCartNotFound_thenOrderProcessingException()
      throws CartNotFoundException {
    Mockito.when(cartService.getUserCart(Mockito.anyString()))
        .thenThrow(CartNotFoundException.class);

    Assert.assertThrows(OrderProcessingException.class,
        () -> orderProcessingService.placeOrderForRegisteredUser(SESSION_ID, USERNAME));
  }

  @Test
  public void testPlaceOrderForRegisteredUser_whenUserNotFound_thenOrderProcessingException()
      throws CartNotFoundException, UserNotFoundException {
    Cart cart = createCart(1L, SESSION_ID, Set.of());
    Mockito.when(cartService.getUserCart(Mockito.anyString())).thenReturn(cart);

    Assert.assertThrows(OrderProcessingException.class,
        () -> orderProcessingService.placeOrderForRegisteredUser(SESSION_ID, USERNAME));
  }

  @Test
  public void testPlaceOrderForAnonymousUser_whenEverythingIsOk_thenSuccess()
      throws CartNotFoundException, OrderProcessingException {
    Set<Game> games = Set.of(
        createGame(1L, "SUPER GAME", GAME_PRICE),
        createGame(2L, "SUPER GAME PART 2", GAME_PRICE)
    );
    Cart cart = createCart(1L, SESSION_ID, games);
    Mockito.when(cartService.getUserCart(Mockito.anyString())).thenReturn(cart);

    Map<String,String> actualResponse = orderProcessingService.placeOrderForAnonymousUser(SESSION_ID);

    Assert.assertTrue(actualResponse.containsKey("SUPER GAME"));
    Assert.assertTrue(actualResponse.containsKey("SUPER GAME PART 2"));
    Assert.assertNotNull(actualResponse.values());
  }
}