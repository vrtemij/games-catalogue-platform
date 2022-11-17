package com.game.store.platform.modules.user.service;


import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.model.entity.RoleEnum;
import com.game.store.platform.modules.user.model.entity.UserEntity;
import com.game.store.platform.modules.user.repository.UserRepository;
import com.game.store.platform.modules.user.service.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    UserEntity userEntity = user.get();
    return new org.springframework.security.core.userdetails.User(
        userEntity.getUsername(),userEntity.getPassword(),
        List.of(new SimpleGrantedAuthority(userEntity.getRole().getValue())));
  }

  @Override
  public User create(RegisterUser userToRegister) throws UserExistsException {
    String username = userToRegister.getUsername();
    String password = userToRegister.getPassword();
    if (Boolean.TRUE.equals(userRepository.existsByUsername(username))) {
      throw new UserExistsException(username);
    }
    userToRegister.setPassword(passwordEncoder.encode(password));
    userToRegister.setRole(RoleEnum.REGISTERED_USER);
    return saveUser(userMapper.fromRegisterUserToEntity(userToRegister));
  }

  @Override
  public User update(User user) throws UserNotFoundException {
    if(Boolean.FALSE.equals(userRepository.existsByUsername(user.getUsername()))) {
      throw new UserNotFoundException("User with name %s doesn't exist, please register",user.getUsername());
    }
    return saveUser(userMapper.toEntity(user));
  }

  @Override
  public User getUserById(Long id) throws UserNotFoundException {
    try {
      UserEntity user = userRepository.getReferenceById(id);
      return userMapper.fromEntity(user);
    } catch (Exception e) {
      throw new UserNotFoundException("User with id %s doesn't exist", String.valueOf(id));
    }
  }

  @Override
  public Boolean existsByUsername(String username) throws UserNotFoundException {
    if (username == null || username.isEmpty()) {
      throw new UserNotFoundException("Username is empty", username);
    }
    return userRepository.existsByUsername(username);
  }

  @Override
  public User findByUsername(String username) throws UserNotFoundException {
    if (username == null || username.isEmpty()) {
      throw new UserNotFoundException("Username is empty", username);
    }
    UserEntity entity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return userMapper.fromEntity(entity);
  }

  private User saveUser(UserEntity user) {
    UserEntity savedUser = userRepository.save(user);
    return userMapper.fromEntity(savedUser);
  }
}
