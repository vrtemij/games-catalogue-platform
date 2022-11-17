package com.game.store.platform.modules.game.controller;

import com.game.store.platform.modules.game.exception.GameExistsException;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.service.GameService;
import com.game.store.platform.shared.PagedListGenericRestDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("games")
@RequiredArgsConstructor
public class GameController {

  private final GameService gameService;

  @GetMapping
  @PreAuthorize("permitAll()")
  public ResponseEntity<PagedListGenericRestDto<Game>> getGames(Pageable pageable) {
    return new ResponseEntity<>(gameService.getGamesPage(pageable), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<Game> findGameById(@PathVariable("id") Long id)
      throws GameNotFoundException {
    return new ResponseEntity<>(gameService.findGameById(id), HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Game> createGame(@Valid @RequestBody Game game) throws GameExistsException {
    return new ResponseEntity<>(gameService.addGameToCatalog(game), HttpStatus.OK);
  }

  @PutMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Game> updateGame(@Valid @RequestBody Game game) throws GameNotFoundException {
    return new ResponseEntity<>(gameService.updateGame(game), HttpStatus.OK);
  }
}
