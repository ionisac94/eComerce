package com.md.demo.service;

import com.md.demo.model.Item;
import com.md.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);


	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = requireNonNull(itemRepository, "itemRepository can not be null");
	}

	public Item getItemById(Integer id) {
		LOGGER.info("About to find Item from DB by id: " + id);

		Optional<Item> byId = itemRepository.findById(id);

		return byId.orElse(null);
	}

	public void deleteComment(Integer id) {
		itemRepository.deleteById(id);
	}
}
