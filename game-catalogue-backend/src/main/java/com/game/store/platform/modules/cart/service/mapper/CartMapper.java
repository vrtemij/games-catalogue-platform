package com.game.store.platform.modules.cart.service.mapper;

import com.game.store.platform.modules.cart.model.Cart;
import com.game.store.platform.modules.cart.model.entity.CartEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

  Cart fromEntity(CartEntity entity);

  CartEntity toEntity(Cart dto);

  List<Cart> fromEntities(List<CartEntity> list);

  List<CartEntity> toEntities(List<Cart> list);

}