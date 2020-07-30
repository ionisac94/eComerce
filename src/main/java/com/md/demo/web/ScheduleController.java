package com.md.demo.web;

import com.md.demo.service.ReloadableItemCacheService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	private final ReloadableItemCacheService reloadableItemCacheService;

	@GetMapping(path = "/cache/clear", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> clearCache() {
		LOGGER.info("Clearing and reloading cache");
		reloadableItemCacheService.loadCache();
		LOGGER.info("Cache cleared and reloaded");

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
