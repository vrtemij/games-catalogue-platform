package com.game.store.platform.modules.user.service;

import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;

public interface UserService {

  User create(RegisterUser user) throws UserExistsException;

  User update(User user) throws UserNotFoundException;

  User getUserById(Long id) throws UserNotFoundException;

  Boolean existsByUsername(String username) throws UserNotFoundException;

  User findByUsername(String username) throws UserNotFoundException;
}
