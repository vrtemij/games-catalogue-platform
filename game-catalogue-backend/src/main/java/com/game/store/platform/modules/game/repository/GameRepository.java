package com.game.store.platform.modules.game.repository;

import com.game.store.platform.modules.game.model.GameCategory;
import com.game.store.platform.modules.game.model.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

  Page<GameEntity> findAllByCategory(GameCategory category, Pageable pageable);

  Boolean existsByName(String name);

  Boolean existsById(String name);

}
