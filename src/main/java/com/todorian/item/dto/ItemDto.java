package com.todorian.item.dto;

import com.todorian.item.domain.ItemCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDto {
    private long itemId;
    private String itemName;
    private String itemDescription;
    private ItemCategory itemCategory;

    @Builder
    public ItemDto(String itemName, String itemDescription, ItemCategory itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }
}