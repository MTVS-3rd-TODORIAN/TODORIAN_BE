package com.todorian.item.dto;

import com.todorian.item.domain.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private long itemId;
    private String itemName;
    private String itemDescription;
    private ItemCategory itemCategory;
}