package com.game.store.platform;

import com.game.store.platform.modules.cart.repository.CartRepository;
import com.game.store.platform.modules.game.model.GameCategory;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import com.game.store.platform.modules.game.repository.GameRepository;
import com.game.store.platform.modules.user.model.entity.RoleEnum;
import com.game.store.platform.modules.user.model.entity.UserEntity;
import com.game.store.platform.modules.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

  private final UserRepository userRepository;

  private final GameRepository gameRepository;

  private final CartRepository cartRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void run(String...args) throws Exception {
    userRepository.deleteAll();
    cartRepository.deleteAll();
    gameRepository.deleteAll();

    userRepository.save(createAdminUser());
    gameRepository.saveAll(createGameEntities());
  }


  private UserEntity createAdminUser() {
    UserEntity admin = new UserEntity();
    admin.setRole(RoleEnum.ADMIN);
    admin.setUsername("admin");
    admin.setPassword(bCryptPasswordEncoder.encode("admin"));
    return admin;
  }

  private List<GameEntity> createGameEntities() {
    GameEntity witcher = GameEntity.builder()
        .name("Witcher")
        .description("Being based on a novel series by Andrzej Sapkowski – a bestselling Polish author – gives the game’s universe and characters credibility, authority and exceptional cohesiveness.")
        .category(GameCategory.ADVENTURE)
        .price("$10")
        .build();

    GameEntity witcher2 = GameEntity.builder()
        .name("Witcher 2 Assasins of Kings")
        .description("A time of untold chaos has come. Mighty forces clash behind the scenes in a struggle for power and influence.")
        .category(GameCategory.ADVENTURE)
        .price("$15")
        .build();

    GameEntity gwent = GameEntity.builder()
        .name("GWENT: The Witcher Card Game")
        .description("Command mighty Witcher-world heroes in epic online PvP card battles! ")
        .category(GameCategory.STRATEGY)
        .price("$1")
        .build();

    return List.of(gwent, witcher, witcher2);
  }
}
