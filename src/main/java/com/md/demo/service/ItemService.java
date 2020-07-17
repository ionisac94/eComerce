package com.md.demo.service;

import com.md.demo.model.Item;

public interface ItemService {

	Item getItemById(Integer id);

	boolean isItemDeleted(Integer id);
}
