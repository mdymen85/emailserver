package com.bmc.emailserver.authentication;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.container.ContainerRequestContext;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.annotation.WebFilter;
import javax.ws.rs.NotAuthorizedException;

@Provider
@PreMatching
//@WebFilter(filterName = "BearerTokenFilter", urlPatterns = { "/email" })
//@WebFilter("/*")
public class BearerTokenFilter implements ContainerRequestFilter {
	
   public void filter(ContainerRequestContext ctx) throws IOException {
      String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
      if (authHeader == null) throw new NotAuthorizedException("Bearer");
      String token = parseToken(authHeader);
      if (verifyToken(token) == false) {
         throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
      }
   }

   private String parseToken(String header) {return "";}
   private boolean verifyToken(String token) {return false;}
}