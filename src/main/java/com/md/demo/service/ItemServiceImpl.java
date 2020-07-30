package com.md.demo.service;

import com.md.demo.exception.NoSuchItemExistException;
import com.md.demo.model.Item;
import com.md.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private final ItemRepository itemRepository;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Item getItemById(Integer id) {
		LOGGER.info("About to find Item from DB by id: " + id);
		Optional<Item> byId = itemRepository.findById(id);

		return byId.orElseThrow(() -> new NoSuchItemExistException("No such item in DB"));
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<Item> getAllItems() {
		return (List<Item>) itemRepository.findAll();
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public boolean isItemDeleted(Integer id) {
		boolean itemIsDeleted = false;
		Item itemById = getItemById(id);
		LOGGER.info("About to delete an Item from DB by id: " + id);
		if (itemById.getAverageRating() != null) {
			itemRepository.deleteById(id);
			return itemIsDeleted = true;
		} else {
			throw new NoSuchItemExistException("No such Item in DB!");
		}
	}
}
