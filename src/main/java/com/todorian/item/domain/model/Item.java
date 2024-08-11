package com.todorian.item.domain.model;

import com.todorian.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_item")
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String itemName;
    private String itemDescription;
    private long itemPrice;
    private ItemCategory itemCategory;

    public Item(String itemName, String itemDescription, long itemPrice,
        ItemCategory itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }

    public Item() {
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
        return "Item{" +
            "itemId=" + itemId +
            ", itemName='" + itemName + '\'' +
            ", itemDescription='" + itemDescription + '\'' +
            ", itemPrice=" + itemPrice +
            ", itemCategory=" + itemCategory +
            '}';
    }
}