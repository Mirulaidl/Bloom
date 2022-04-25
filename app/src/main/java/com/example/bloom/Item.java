package com.example.bloom;

public class Item {

    public String itemName, itemPrice, image, itemDescription, itemCategory;

    public Item(){

    }

    public Item(String itemName, String itemPrice, String image, String itemDescription, String itemCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.image = image;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }


    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImageID(String imageID) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String ItemName) {
        itemName = ItemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String ItemPrice) {
        itemPrice = ItemPrice;
    }




}
