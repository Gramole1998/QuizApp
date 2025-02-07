package com.gaurav.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;


import com.gaurav.springboot.util.JwtUtil;

import io.netty.handler.codec.http.HttpStatusClass;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	public static final Logger logger=LoggerFactory.getLogger(AuthenticationFilter.class);

	@Autowired
	JwtUtil util;

	
	public AuthenticationFilter() {
		super(Config.class);
		
	}

	public static class Config {
		public Config() {
			
		}
	}

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return (exchange,chain)->{
			String authHeader=exchange.getRequest().getHeaders().getFirst("Authorization");
			System.out.println(authHeader);
			String userName;
			if(authHeader ==null || !authHeader.startsWith("Bearer ") ) {
				 logger.info("inside AuthHeader Check");
				 exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				 return exchange.getResponse().setComplete();
			}
			String token=authHeader.substring(7);
			System.out.println(token);
			userName=util.extractUserName(token);
			System.out.println(userName);
			if(userName!=null && util.validToken(userName,token)) {
				exchange.getRequest().mutate().header("x-Authenticated-User", userName).build();
			}
			else {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			
			return chain.filter(exchange);
			
		};
	}
}
