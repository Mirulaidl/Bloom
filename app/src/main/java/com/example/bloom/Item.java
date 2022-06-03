package com.example.bloom;

public class Item {

    public String itemName, itemPrice, image, itemDescription, itemCategory, quantity, address1, address2, additionalnumber, sellerID, buyerID, orderID;

    public Item(){

    }

    public Item(String itemName, String itemPrice, String image, String itemDescription, String itemCategory, String quantity, String address1, String address2, String additionalnumber, String sellerID, String buyerID) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.image = image;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.quantity = quantity;
        this.address1 = address1;
        this.address2 = address2;
        this.additionalnumber = additionalnumber;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.orderID = orderID;

    }

    public String getQuantity() { return quantity;}

    public void setQuantity(String quantity) {this.quantity = quantity;}

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAdditionalnumber() {
        return additionalnumber;
    }

    public void setAdditionalnumber(String additionalnumber) {
        this.additionalnumber = additionalnumber;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
