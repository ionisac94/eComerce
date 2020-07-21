package com.md.demo.service;

import com.md.demo.exception.NoSuchItemExistException;
import com.md.demo.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class ReloadableItemCacheServiceImpl implements ReloadableItemCacheService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReloadableItemCacheServiceImpl.class);


	private List<Item> allItems;

	private ItemService itemService;

	public ReloadableItemCacheServiceImpl(ItemService itemService) {
		this.itemService = Objects.requireNonNull(itemService, "itemService is mandatory");
	}

	@PostConstruct
	public void initialCacheLoad() {
		loadCache();

		if (isNull(allItems)) {
			throw new NoSuchItemExistException("No items are found in DB");
		}
	}

	@Scheduled(fixedDelayString = "${ecomerce.cache.refreshRate}")
	@Override
	public void loadCache() {
		LOGGER.info("Started cache loading");
		try {
			allItems = itemService.getAllItems();
			LOGGER.info("Cache loaded with {} entities", allItems.size());
		} catch (Exception ex) {
			LOGGER.error("Unable to retrieve items from DB", ex);
		}
	}

	@Override
	public boolean cacheContainsItems(String itemId) {
		return allItems.contains(itemId);
	}
}
