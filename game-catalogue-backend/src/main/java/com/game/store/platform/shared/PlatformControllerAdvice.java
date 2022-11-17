package com.game.store.platform.shared;

import com.game.store.platform.modules.cart.exception.CartNotFoundException;
import com.game.store.platform.modules.game.exception.GameExistsException;
import com.game.store.platform.modules.game.exception.GameNotFoundException;
import com.game.store.platform.modules.order.exception.OrderProcessingException;
import com.game.store.platform.modules.user.exception.UserExistsException;
import com.game.store.platform.modules.user.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PlatformControllerAdvice extends ResponseEntityExceptionHandler {

  private static final String TIMESTAMP = "timestamp";
  private static final String MESSAGE = "message";
  private static final String CAUSE = "cause";

  @ExceptionHandler({CartNotFoundException.class, GameNotFoundException.class, UserNotFoundException.class, })
  protected ResponseEntity<Object> handleCartNotFoundException(Exception ex,
      WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({UserExistsException.class, GameExistsException.class})
  protected ResponseEntity<Object> handleUserExistsExceptionException(Exception ex,
      WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(AuthenticationException.class)
  protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex,
      WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, "Authentication failed");
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
      WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(TIMESTAMP, LocalDateTime.now());
    body.put(MESSAGE, ex.getMessage());
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

    @ExceptionHandler(OrderProcessingException.class)
    protected ResponseEntity<Object> handleOrderProcessingException(OrderProcessingException ex, WebRequest request) {
      Map<String, Object> body = new LinkedHashMap<>();
      body.put(TIMESTAMP, LocalDateTime.now());
      body.put(MESSAGE, ex.getMessage());
      body.put(CAUSE, ex.getCause().getMessage());
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
