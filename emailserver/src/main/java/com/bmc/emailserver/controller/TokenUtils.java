package com.bmc.emailserver.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenUtils {

	public static String getUsername(String token) {
		
		   var claims = (Claims) Jwts.parser()
				   .setSigningKey("FREE_MASON")
				   .parseClaimsJws(token)
				   .getBody();
		   
		   return claims.getIssuer();
		
	}
	
}
