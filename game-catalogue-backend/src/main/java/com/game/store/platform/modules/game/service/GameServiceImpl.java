package com.game.store.platform.modules.game.service;

import com.game.store.platform.modules.game.exception.GameExistsException;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.GameCategory;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import com.game.store.platform.modules.game.repository.GameRepository;
import com.game.store.platform.modules.game.service.mapper.GameMapper;
import com.game.store.platform.shared.PagedListGenericRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  private final GameMapper gameMapper;

  @Override
  public PagedListGenericRestDto<Game> getGamesPage(Pageable pageable) {
    PagedListGenericRestDto<Game> gamesDtoPage = new PagedListGenericRestDto<>();
    Page<GameEntity> gamesEntityPage = gameRepository.findAll(pageable);

    gamesDtoPage.setItems(gameMapper.fromEntities(gamesEntityPage.getContent()));
    gamesDtoPage.setTotalCount(gamesEntityPage.getTotalElements());
    return gamesDtoPage;
  }

  @Override
  public PagedListGenericRestDto<Game> getGamesPageByCategory(GameCategory category,
      Pageable pageable) {
    PagedListGenericRestDto<Game> gamesDtoPage = new PagedListGenericRestDto<>();
    Page<GameEntity> gamesEntityPage = gameRepository.findAllByCategory(category, pageable);

    gamesDtoPage.setItems(gameMapper.fromEntities(gamesEntityPage.getContent()));
    gamesDtoPage.setTotalCount(gamesEntityPage.getTotalElements());
    return gamesDtoPage;
  }

  @Override
  public Game findGameById(Long gameId) throws GameNotFoundException {
    if (gameId == null) {
      throw new IllegalArgumentException("Game id should not be null");
    }
    GameEntity game = gameRepository.findById(gameId)
        .orElseThrow(() -> new GameNotFoundException(gameId));
    return gameMapper.fromEntity(game);
  }

  @Override
  public Game addGameToCatalog(Game game) throws GameExistsException {
    if (Boolean.TRUE.equals(gameRepository.existsByName(game.getName()))) {
      throw new GameExistsException(game.getName());
    }
    GameEntity saved = gameRepository.save(gameMapper.toEntity(game));
    return gameMapper.fromEntity(saved);
  }

  @Override
  public Game updateGame(Game game) throws GameNotFoundException {
    if (!gameRepository.existsById(game.getId())) {
      throw new GameNotFoundException(game.getId());
    }
    GameEntity saved = gameRepository.save(gameMapper.toEntity(game));
    return gameMapper.fromEntity(saved);
  }
}
