package com.game.store.platform.modules.user.service.mapper;

import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.model.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "games", ignore = true)
  UserEntity fromRegisterUserToEntity(RegisterUser registerUser);

  User fromEntity(UserEntity entity);

  UserEntity toEntity(User dto);

  List<User> fromEntities(List<UserEntity> list);

  List<UserEntity> toEntities(List<User> list);
}
