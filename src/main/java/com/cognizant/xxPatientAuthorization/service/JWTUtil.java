package com.cognizant.xxPatientAuthorization.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
	
	private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
	
	private String secretKey = "secret";
	
	public String extractUsername(String token)
	{
		logger.info("METHOD EXECUTION START");
		logger.info("METHOD EXECUTION END");
		
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token)
	{
		logger.info("METHOD EXECUTION START");
		logger.info("METHOD EXECUTION END");
		
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		logger.info("METHOD EXECUTION START");
		
		final Claims claims = extractAllClaims(token);
		
		logger.info("METHOD EXECUTION END");
		
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token)
	{
		logger.info("METHOD EXECUTION START");
		logger.info("METHOD EXECUTION END");
		
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	public Boolean isTokenExpired(String token)
	{
		logger.info("METHOD EXECUTION START");
		logger.info("METHOD EXECUTION END");
		
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails)
	{
		logger.info("METHOD EXECUTION START");
		
		Map<String, Object> claims = new HashMap<>();
		
		logger.info("METHOD EXECUTION START");
		
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims, String subject)
	{
		logger.info("METHOD EXECUTION START");
		
		String compact = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000*60*720)))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		
		logger.info("METHOD EXECUTION START");
		
		return compact;
	}
	
	public Boolean validateToken(String token)
	{
		logger.info("METHOD EXECUTION START");
		
		try
		{
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			logger.info("METHOD EXECUTION START");
			
			return true;
		}
		
		catch (Exception e)
		{
			logger.info("EXCEPTION THROWN");
			return false;
		}
	}
	
	

}
