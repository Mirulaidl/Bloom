package com.example.bloom;

public class Item {

    public String itemName, itemPrice, image;

    public Item(){

    }

    public Item(String itemName, String itemPrice, String image) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.image = image;
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
