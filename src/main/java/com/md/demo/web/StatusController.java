package com.md.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatusController for the application.
 */

@RestController
public class StatusController {

	@Autowired
	private HealthEndpoint healthEndpoint;

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
