package com.game.store.platform.modules.cart.repository;

import com.game.store.platform.modules.cart.model.entity.CartEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

  Optional<CartEntity> findBySessionId(String sessionId);

  void deleteById(Long id);
}
