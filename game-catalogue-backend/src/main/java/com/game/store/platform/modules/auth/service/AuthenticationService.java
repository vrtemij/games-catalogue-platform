package com.game.store.platform.modules.auth.service;

import com.game.store.platform.modules.auth.model.UserCredentials;
import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    String login(UserCredentials authDto)
        throws AuthenticationException, UserNotFoundException;

    User register(RegisterUser registerUserDto) throws UserExistsException;
}
