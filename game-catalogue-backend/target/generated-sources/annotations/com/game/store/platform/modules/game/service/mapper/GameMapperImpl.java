package com.game.store.platform.modules.game.service.mapper;

import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-17T14:25:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public Game fromEntity(GameEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Game.GameBuilder game = Game.builder();

        game.id( entity.getId() );
        game.name( entity.getName() );
        game.description( entity.getDescription() );
        game.category( entity.getCategory() );
        game.price( entity.getPrice() );

        return game.build();
    }

    @Override
    public GameEntity toEntity(Game dto) {
        if ( dto == null ) {
            return null;
        }

        GameEntity.GameEntityBuilder gameEntity = GameEntity.builder();

        gameEntity.id( dto.getId() );
        gameEntity.name( dto.getName() );
        gameEntity.description( dto.getDescription() );
        gameEntity.category( dto.getCategory() );
        gameEntity.price( dto.getPrice() );

        return gameEntity.build();
    }

    @Override
    public List<Game> fromEntities(List<GameEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Game> list1 = new ArrayList<Game>( list.size() );
        for ( GameEntity gameEntity : list ) {
            list1.add( fromEntity( gameEntity ) );
        }

        return list1;
    }

    @Override
    public List<GameEntity> toEntities(List<Game> list) {
        if ( list == null ) {
            return null;
        }

        List<GameEntity> list1 = new ArrayList<GameEntity>( list.size() );
        for ( Game game : list ) {
            list1.add( toEntity( game ) );
        }

        return list1;
    }
}
