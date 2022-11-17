package com.game.store.platform.modules.cart.service.mapper;

import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.model.entity.CartEntity;
import com.game.store.platform.modules.game.model.Game;
import com.game.store.platform.modules.game.model.entity.GameEntity;
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
public class CartMapperImpl implements CartMapper {

    @Override
    public Cart fromEntity(CartEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Cart.CartBuilder cart = Cart.builder();

        cart.id( entity.getId() );
        cart.sessionId( entity.getSessionId() );
        cart.games( gameEntitySetToGameSet( entity.getGames() ) );

        return cart.build();
    }

    @Override
    public CartEntity toEntity(Cart dto) {
        if ( dto == null ) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId( dto.getId() );
        cartEntity.setSessionId( dto.getSessionId() );
        cartEntity.setGames( gameSetToGameEntitySet( dto.getGames() ) );

        return cartEntity;
    }

    @Override
    public List<Cart> fromEntities(List<CartEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Cart> list1 = new ArrayList<Cart>( list.size() );
        for ( CartEntity cartEntity : list ) {
            list1.add( fromEntity( cartEntity ) );
        }

        return list1;
    }

    @Override
    public List<CartEntity> toEntities(List<Cart> list) {
        if ( list == null ) {
            return null;
        }

        List<CartEntity> list1 = new ArrayList<CartEntity>( list.size() );
        for ( Cart cart : list ) {
            list1.add( toEntity( cart ) );
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
