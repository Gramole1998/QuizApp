package com.gaurav.springboot.util;

import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//
//import com.gaurav.springboot.webclient.Webclient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient;


@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey;
	
//	private final WebClient webClient;
//	
//	@Autowired
//    public JwtUtil(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.build();  // Customize as needed
//    }
	

	private static final Logger logger=LoggerFactory.getLogger(JwtUtil.class);
	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		logger.info("inside extract Method");
		String userName=extractClaims(token,Claims::getSubject);
		logger.info("expected username",userName);
		return extractClaims(token,Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		logger.info("inside All Extract claim method");
		Claims claim=extractAllclaims(token);
		return claimResolver.apply(claim);
		
	}

	private Claims extractAllclaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getkey())
				.build().
				parseClaimsJws(token).
				getBody();
	}

	public boolean validToken(String username, String token) {
		// TODO Auto-generated method stub
		String uname=extractUserName(token);
		return uname.equals(username) && !tokenExpire(token);
	}
	
	private boolean tokenExpire(String token) {
		// TODO Auto-generated method stub
		return getExpiration(token).before(new Date());
	}

	private <T> Date getExpiration(String token ) {
		return extractClaims(token, Claims::getExpiration);
	}

	private SecretKey getkey() {
		// TODO Auto-generated method stub
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
	
//	   public Boolean getUser(String name) {
//	        return webClient
//	                .get()
//	                .uri("/lb://QUIZ-SERVICE/user/userDetail", name)  // Add parameters
//	                .retrieve()
//	                .bodyToMono(Boolean.class).subscribe(active); // Mono will return a reactive result
//	    }
}
	

