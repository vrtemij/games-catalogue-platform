package com.game.store.platform.modules.game.model.entity;

import com.game.store.platform.modules.game.model.GameCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class GameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private GameCategory category;

  @Column(name = "price")
  private String price;
}
