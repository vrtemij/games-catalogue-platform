package com.game.store.platform.modules.user.service;

import static com.game.store.platform.modules.utils.TestUtil.USERNAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.model.RegisterUser;
import com.game.store.platform.modules.user.model.User;
import com.game.store.platform.modules.user.model.entity.RoleEnum;
import com.game.store.platform.modules.user.model.entity.UserEntity;
import com.game.store.platform.modules.user.repository.UserRepository;
import com.game.store.platform.modules.user.service.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Spy
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Spy
  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(userService, "userMapper", userMapper);
    ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);
  }

  @Test
  public void testCreate_whenEverythingIsOk_thenSuccess() throws UserExistsException {
    RegisterUser registerUser = new RegisterUser(USERNAME, "123", RoleEnum.REGISTERED_USER);
    UserEntity user = userMapper.fromRegisterUserToEntity(registerUser);

    Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
    Mockito.when(userRepository.save(any())).thenReturn(user);

    User actualUser = userService.create(registerUser);

    Assert.assertNotNull(actualUser);
  }

  @Test
  public void testCreate_whenUserExists_thenThrowException() {
    RegisterUser registerUser = new RegisterUser(USERNAME, "123", RoleEnum.REGISTERED_USER);

    Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);

    Assert.assertThrows(UserExistsException.class, () -> userService.create(registerUser));
  }
}