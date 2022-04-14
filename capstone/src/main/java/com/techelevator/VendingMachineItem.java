package com.techelevator;

public class VendingMachineItem {
    private String itemName;
    private double price;
    private String slot;
    private int quantity = 5;
    private String type;

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getSlot() {
        return slot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public VendingMachineItem(String slot, String itemName, double price, String type) {
        this.itemName = itemName;
        this.price = price;
        this.slot = slot;
        this.type = type;
    }

    public String toString() {
        String itemString = this.slot + ", " + this.itemName + ", $" + this.price + ", " + "Quantity: " + this.quantity;
        if (this.quantity == 0) {
            return itemString + ", SOLD OUT!";
        } else return itemString;
    }

    public void printFlavorText() {
        if (this.type.equals("Chip")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (this.type.equals("Candy")) {
            System.out.println("Munch Munch, Yum!");
        }else if (this.type.equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
        }else if (this.type.equals("Gum")) {
            System.out.println("Chew Chew, Yum!");
        }
    }
}
