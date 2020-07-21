package com.md.demo.service;

import com.md.demo.model.Item;

import java.util.List;

public interface ItemService {

	Item getItemById(Integer id);

	boolean isItemDeleted(Integer id);

	List<Item> getAllItems();
}
