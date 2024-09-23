package com.todorian.item.domain.model;

public class ItemCreateDto {

    private long itemId;
    private String itemName;
    private String itemDescription;
    private long itemPrice;
    private ItemCategory itemCategory;

    public ItemCreateDto(String itemName, String itemDescription, long itemPrice,
        ItemCategory itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    @Override
    public String toString() {
        return "ItemCreateDto{" +
            "itemId=" + itemId +
            ", itemName='" + itemName + '\'' +
            ", itemDescription='" + itemDescription + '\'' +
            ", itemPrice=" + itemPrice +
            ", itemCategory=" + itemCategory +
            '}';
    }
}