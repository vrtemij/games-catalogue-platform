package com.game.store.platform.modules.game.service;

import static com.game.store.platform.modules.utils.TestUtil.GAME_PRICE;
import static com.game.store.platform.modules.utils.TestUtil.createGame;
import static org.mockito.ArgumentMatchers.anyLong;

import com.game.store.platform.modules.game.exception.GameExistsException;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import com.game.store.platform.modules.game.repository.GameRepository;
import com.game.store.platform.modules.game.service.mapper.GameMapper;
import com.game.store.platform.shared.PagedListGenericRestDto;
import java.util.List;
import java.util.Optional;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

  @InjectMocks
  private GameServiceImpl gameService;

  @Mock
  private GameRepository gameRepository;

  @Spy
  private GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(gameService, "gameMapper", gameMapper);
  }

  @Test
  public void testGetGamesPage_whenEverythingIsOk_thenReturnRestDto() {
    PageRequest pageable = PageRequest.of(0, 10);
    Game game = createGame(1L, "SUPER GAME", GAME_PRICE);
    PageImpl<GameEntity> page = new PageImpl<>(List.of(gameMapper.toEntity(game)));

    Mockito.when(gameRepository.findAll(pageable)).thenReturn(page);

    PagedListGenericRestDto<Game> actualResponse = gameService.getGamesPage(PageRequest.of(0, 10));
    Assert.assertNotNull(actualResponse.getItems());
    Assert.assertEquals(page.getContent().get(0).getName(), actualResponse.getItems().get(0).getName());
  }

  @Test
  public void testFindGameById_whenEverythingIsOk_thenReturnGame() throws GameNotFoundException {
    Game game = createGame(1L, "SUPER GAME", GAME_PRICE);
    GameEntity gameEntity = gameMapper.toEntity(game);

    Mockito.when(gameRepository.findById(anyLong())).thenReturn(Optional.of(gameEntity));

    Game actualGame = gameService.findGameById(1L);
    Assert.assertNotNull(actualGame);
  }

  @Test
  public void testAddGameToCatalog_whenEverythingIsOk_thenReturnGame()
      throws GameExistsException {
    Game game = createGame(1L, "SUPER GAME", GAME_PRICE);
    GameEntity gameEntity = gameMapper.toEntity(game);

    Mockito.when(gameRepository.save(gameEntity)).thenReturn(gameEntity);

    Game actualGame = gameService.addGameToCatalog(game);
    Assert.assertNotNull(actualGame);
  }


}