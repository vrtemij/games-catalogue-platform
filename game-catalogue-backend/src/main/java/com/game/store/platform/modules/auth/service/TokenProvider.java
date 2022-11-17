package com.game.store.platform.modules.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenProvider implements Serializable {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.token.validity:360000}")
  private long validityMilliseconds;

  private static final String USER_ID_KEY = "userid";

  private static final String AUTHORITIES_KEY = "authorities";

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Long getUserIdFromToken(String token) {
    return getAllClaimsFromToken(token).get(USER_ID_KEY, Long.class);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(Authentication authentication) {
    Date now = new Date(System.currentTimeMillis());
    final String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim(AUTHORITIES_KEY, authorities)
        .signWith(SignatureAlgorithm.HS256, secret)
        .setIssuedAt(now)
        .setExpiration(new Date(now.toInstant().toEpochMilli() + validityMilliseconds))
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  UsernamePasswordAuthenticationToken getAuthentication(final String token,
      final UserDetails userDetails) {
    final JwtParser jwtParser = Jwts.parser().setSigningKey(secret);
    final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
    final Claims claims = claimsJws.getBody();

    final String authoritiesString = claims.get(AUTHORITIES_KEY).toString();
    final Collection<? extends GrantedAuthority> authorities =
        StringUtils.hasText(authoritiesString)
            ? Arrays.stream(authoritiesString.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList())
            : new ArrayList<>();

    return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
  }

}
