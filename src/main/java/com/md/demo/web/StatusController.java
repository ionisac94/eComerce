package com.md.demo.web;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatusController for the application.
 */
@Api(description = "An endpoint for DevOps team to check this app if is up and running")
@AllArgsConstructor
@RestController
public class StatusController {

	private final HealthEndpoint healthEndpoint;

	@GetMapping(path = "/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Status getStatus() {
		return health();
	}

	@PostMapping(path = "/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Status postStatus() {
		return health();
	}

	private Status health() {
		Health health = healthEndpoint.health();
		return health.getStatus();
	}
}
