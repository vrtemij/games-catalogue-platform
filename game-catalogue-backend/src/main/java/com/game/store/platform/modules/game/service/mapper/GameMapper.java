package com.game.store.platform.modules.game.service.mapper;

import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

  Game fromEntity(GameEntity entity);

  GameEntity toEntity(Game dto);

  List<Game> fromEntities(List<GameEntity> list);

  List<GameEntity> toEntities(List<Game> list);
}
