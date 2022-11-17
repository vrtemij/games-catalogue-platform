package com.game.store.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJdbcHttpSession
@EnableSwagger2
@EnableTransactionManagement
public class PlatformApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlatformApplication.class, args);
  }

}
