package com.game.store.platform.modules.auth.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  @Qualifier("userService")
  private UserDetailsService userDetailsService;

  @Autowired
  private TokenProvider tokenProvider;

  private static final String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HttpHeaders.AUTHORIZATION);
    String username = null;
    String authToken = null;
    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      authToken = header.replace(TOKEN_PREFIX, "");
      try {
        username = tokenProvider.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        logger.error("an error occurred during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("The token is expired and not valid anymore");
      } catch (SignatureException e) {
        logger.error("Authentication Failed. Username or Password not valid.");
      }
    } else {
      logger.warn("Couldn't find bearer string, will ignore the header");
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (tokenProvider.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(
            authToken, userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(req, res);
  }
}
