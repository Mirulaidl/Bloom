package com.example.bloom;

public class Item {

    public String itemName, itemPrice, imageID;

    public Item(){

    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
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

    public Item(String itemName, String itemPrice, String imageID) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.imageID = imageID;
    }


}
