package com.game.store.platform.modules.user.service.mapper;

import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.model.entity.UserEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-17T14:25:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity fromRegisterUserToEntity(RegisterUser registerUser) {
        if ( registerUser == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( registerUser.getUsername() );
        userEntity.setPassword( registerUser.getPassword() );
        userEntity.setRole( registerUser.getRole() );

        return userEntity;
    }

    @Override
    public User fromEntity(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( entity.getId() );
        user.username( entity.getUsername() );
        user.role( entity.getRole() );
        user.games( gameEntitySetToGameSet( entity.getGames() ) );

        return user.build();
    }

    @Override
    public UserEntity toEntity(User dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setUsername( dto.getUsername() );
        userEntity.setRole( dto.getRole() );
        userEntity.setGames( gameSetToGameEntitySet( dto.getGames() ) );

        return userEntity;
    }

    @Override
    public List<User> fromEntities(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( fromEntity( userEntity ) );
        }

        return list1;
    }

    @Override
    public List<UserEntity> toEntities(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserEntity> list1 = new ArrayList<UserEntity>( list.size() );
        for ( User user : list ) {
            list1.add( toEntity( user ) );
        }

        return list1;
    }

    protected Game gameEntityToGame(GameEntity gameEntity) {
        if ( gameEntity == null ) {
            return null;
        }

        Game.GameBuilder game = Game.builder();

        game.id( gameEntity.getId() );
        game.name( gameEntity.getName() );
        game.description( gameEntity.getDescription() );
        game.category( gameEntity.getCategory() );
        game.price( gameEntity.getPrice() );

        return game.build();
    }

    protected Set<Game> gameEntitySetToGameSet(Set<GameEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Game> set1 = new LinkedHashSet<Game>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( GameEntity gameEntity : set ) {
            set1.add( gameEntityToGame( gameEntity ) );
        }

        return set1;
    }

    protected GameEntity gameToGameEntity(Game game) {
        if ( game == null ) {
            return null;
        }

        GameEntity.GameEntityBuilder gameEntity = GameEntity.builder();

        gameEntity.id( game.getId() );
        gameEntity.name( game.getName() );
        gameEntity.description( game.getDescription() );
        gameEntity.category( game.getCategory() );
        gameEntity.price( game.getPrice() );

        return gameEntity.build();
    }

    protected Set<GameEntity> gameSetToGameEntitySet(Set<Game> set) {
        if ( set == null ) {
            return null;
        }

        Set<GameEntity> set1 = new LinkedHashSet<GameEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Game game : set ) {
            set1.add( gameToGameEntity( game ) );
        }

        return set1;
    }
}
