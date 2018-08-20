package com.joh.phms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

	public SecurityInitializer() {
		logger.info("SecurityInitializer->fired");
	}
}
