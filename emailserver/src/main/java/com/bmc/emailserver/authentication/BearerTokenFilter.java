package com.bmc.emailserver.authentication;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import com.bmc.emailserver.controller.TokenUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.ws.rs.container.ContainerRequestContext;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.annotation.WebFilter;
import javax.ws.rs.NotAuthorizedException;

@Provider
@PreMatching
public class BearerTokenFilter implements ContainerRequestFilter {
	
   public void filter(ContainerRequestContext ctx) throws IOException {
      String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
      if (authHeader == null) throw new NotAuthorizedException("Bearer");
      String user = TokenUtils.getUsername(authHeader);
      if (verifyToken(user) == false) {
         throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
      }
   }
   
   private boolean verifyToken(String user) { return user != null; }
}