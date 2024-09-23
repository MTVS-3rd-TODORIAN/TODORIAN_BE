package com.todorian.item.domain.service;

import com.todorian.item.domain.model.Item;
import com.todorian.item.domain.model.ItemCreateDto;
import com.todorian.item.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(ItemCreateDto itemCreateDto) {
        Item item = new Item(
            itemCreateDto.getItemName(),
            itemCreateDto.getItemDescription(),
            itemCreateDto.getItemPrice(),
            itemCreateDto.getItemCategory()
        );
        itemRepository.save(item);
    }
}