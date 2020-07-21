package com.md.demo.web;

import com.md.demo.service.ReloadableItemCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ReloadableItemCacheService reloadableItemCacheService;

	@GetMapping(path = "/cache/clear", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> clearCache() {
		LOGGER.info("Clearing and reloading cache");
		reloadableItemCacheService.loadCache();
		LOGGER.info("Cache cleared and reloaded");

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
