package com.game.store.platform.modules.game.service;

import com.game.store.platform.modules.game.exception.GameExistsException;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.GameCategory;
import com.game.store.platform.shared.PagedListGenericRestDto;
import org.springframework.data.domain.Pageable;

public interface GameService {

  PagedListGenericRestDto<Game> getGamesPage(Pageable pageable);

  PagedListGenericRestDto<Game> getGamesPageByCategory(GameCategory category, Pageable pageable);

  Game findGameById(Long gameId) throws GameNotFoundException;

  Game addGameToCatalog(Game game) throws GameExistsException;

  Game updateGame(Game game) throws GameNotFoundException;

}
