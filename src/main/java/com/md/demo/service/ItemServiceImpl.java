package com.md.demo.service;

import com.md.demo.model.Item;
import com.md.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private ItemRepository itemRepository;

	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = requireNonNull(itemRepository, "itemRepository can not be null");
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Item getItemById(Integer id) {
		LOGGER.info("About to find Item from DB by id: " + id);
		Optional<Item> byId = itemRepository.findById(id);
		return byId.orElse(null);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteItem(Integer id) {
		itemRepository.deleteById(id);
	}
}
