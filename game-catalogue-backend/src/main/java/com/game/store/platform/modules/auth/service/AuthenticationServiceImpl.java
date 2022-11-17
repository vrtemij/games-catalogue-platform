package com.game.store.platform.modules.auth.service;


import com.game.store.platform.modules.auth.model.UserCredentials;
import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final UserService userService;

    @Override
    public String login(UserCredentials authDto)
        throws AuthenticationException, UserNotFoundException {
        String username = authDto.getUsername();
        String password = authDto.getPassword();
        if (Boolean.FALSE.equals(userService.existsByUsername(username))) {
            throw new UserNotFoundException("User with name %s doesn't exist, please register", username);
        }
        return authenticate(username, password);
    }

    @Override
    public User register(RegisterUser registerUserDto) throws UserExistsException {
        return userService.create(registerUserDto);
    }

    private String authenticate(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }
}
