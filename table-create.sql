CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            CHAR(36) NOT NULL,
    SESSION_ID            CHAR(36) NOT NULL,
    CREATION_TIME         BIGINT   NOT NULL,
    LAST_ACCESS_TIME      BIGINT   NOT NULL,
    MAX_INACTIVE_INTERVAL INT      NOT NULL,
    EXPIRY_TIME           BIGINT   NOT NULL,
    PRINCIPAL_NAME        VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID CHAR(36)     NOT NULL,
    ATTRIBUTE_NAME     VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES    BLOB         NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
) ENGINE = InnoDB
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `SPRING_SESSION`
(
    `PRIMARY_ID`            char(36) NOT NULL,
    `SESSION_ID`            char(36) NOT NULL,
    `CREATION_TIME`         bigint   NOT NULL,
    `LAST_ACCESS_TIME`      bigint   NOT NULL,
    `MAX_INACTIVE_INTERVAL` int      NOT NULL,
    `EXPIRY_TIME`           bigint   NOT NULL,
    `PRINCIPAL_NAME`        varchar(100) DEFAULT NULL,
    PRIMARY KEY (`PRIMARY_ID`),
    KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC

CREATE TABLE `SPRING_SESSION_ATTRIBUTES`
(
    `SESSION_PRIMARY_ID` char(36)     NOT NULL,
    `ATTRIBUTE_NAME`     varchar(200) NOT NULL,
    `ATTRIBUTE_BYTES`    blob         NOT NULL,
    PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
    CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC

CREATE TABLE `cart`
(
    `id`         bigint NOT NULL,
    `session_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE `cart_games`
(
    `cart_entity_id` bigint NOT NULL,
    `games_id`       bigint NOT NULL,
    PRIMARY KEY (`cart_entity_id`, `games_id`),
    KEY `FKdft3gl99m7nuhvykitwx78qls` (`games_id`),
    CONSTRAINT `FKdft3gl99m7nuhvykitwx78qls` FOREIGN KEY (`games_id`) REFERENCES `games` (`id`),
    CONSTRAINT `FKoot3ido15uk9l3t6lwd4e2ywo` FOREIGN KEY (`cart_entity_id`) REFERENCES `cart` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE `games`
(
    `id`          bigint NOT NULL,
    `category`    varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `price`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKdp39yy9j9cn10v9vhyr2j1uaa` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE `hibernate_sequence`
(
    `next_val` bigint DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE `users`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `password` varchar(255) DEFAULT NULL,
    `role`     varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

CREATE TABLE `users_games`
(
    `user_entity_id` bigint NOT NULL,
    `games_id`       bigint NOT NULL,
    PRIMARY KEY (`user_entity_id`, `games_id`),
    KEY `FK5g5omrf817ocsxxh1wag30ece` (`games_id`),
    CONSTRAINT `FK5g5omrf817ocsxxh1wag30ece` FOREIGN KEY (`games_id`) REFERENCES `games` (`id`),
    CONSTRAINT `FKs49ypeuks4ob0kg1pkxtx3364` FOREIGN KEY (`user_entity_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

INSERT INTO `users` VALUES (1, `123`, `ADMIN`, `admin`)
