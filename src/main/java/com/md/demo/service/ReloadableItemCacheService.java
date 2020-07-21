package com.md.demo.service;

public interface ReloadableItemCacheService {

	void loadCache();

	boolean cacheContainsItems(String itemId);
}
