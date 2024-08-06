package com.todorian.item.service;

import com.todorian.item.domain.Item;
import com.todorian.item.dto.ItemDto;
import com.todorian.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(ItemDto itemDto) {
        Item item = Item.builder()
            .itemId(itemDto.getItemId())
            .build();

        itemRepository.save(item);
    }
}