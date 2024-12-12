package com.gaurav.springboot.service;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.hc.core5.function.Decorator;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gaurav.springboot.quiz.User;
import com.netflix.spectator.impl.Hash64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

@Service
public class JWTService {

	public String secretKey = "";
	
	private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

	public JWTService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResponseEntity<String> generateToken(User user) {
		// TODO Auto-generated method stub
		Map<String, Object> claims = new HashMap<>();

		return new ResponseEntity<>(Jwts.builder().claims().add(claims).subject(user.getUserName())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 10)).and()
				.signWith(getkey())
				.compact(), HttpStatus.OK);
	}

	private SecretKey getkey() {
		// TODO Auto-generated method stub
		byte[] keyByte = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyByte);
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub

		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetail) {
		// TODO Auto-generated method stub
		final String userName = extractUserName(token);
//		System.out.println(userName);
//		logger.info(userName);
//		System.out.println(userDetail.getUsername());
//		System.out.println(userName.equals(userDetail.getUsername()));
//		String s = Boolean.toString(isTokenExpire(token));
		//logger.info(s);
		return (userName.equals(userDetail.getUsername()) && isTokenExpire(token));
	}

	private boolean isTokenExpire(String token) {
		// TODO Auto-generated method stub
		boolean flag=false;
		
		flag=currentDate().before(extractExpiration(token));
	    
		logger.info("JWT Token Expiration Date: {}", extractExpiration(token));
		logger.info("JWT Token Expiration Date: {}", currentDate());
		System.out.println(flag);
		return flag;
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub

		return extractClaim(token, Claims::getExpiration);
	}
	private Date currentDate() {
		// TODO Auto-generated method stub

		return new Date();
	}

	
}
