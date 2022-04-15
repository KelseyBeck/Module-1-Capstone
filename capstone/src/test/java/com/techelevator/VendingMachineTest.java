package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {
    @Test
    public void makeItemListMakesListFromFile() {
        //Arrange
        VendingMachine test = new VendingMachine();
        List<VendingMachineItem> trueList = new ArrayList<>();
        trueList.add(0, new VendingMachineItem("A1","Potato Crisps",3.05,"Chip"));
        trueList.add(1, new VendingMachineItem("A2", "Stackers", 1.45, "Chip"));
        trueList.add(2, new VendingMachineItem("A3", "Grain Waves", 2.75, "Chip"));
        trueList.add(3, new VendingMachineItem("A4", "Cloud Popcorn", 3.65, "Chip"));
        trueList.add(4, new VendingMachineItem("B1", "Moonpie", 1.80, "Candy"));
        trueList.add(5,new VendingMachineItem("B2", "Cowtales", 1.50, "Candy"));
        trueList.add(6, new VendingMachineItem("B3", "Wonka Bar", 1.50, "Candy"));
        trueList.add(7, new VendingMachineItem("B4", "Crunchie", 1.75, "Candy"));
        trueList.add(8, new VendingMachineItem("C1", "Cola", 1.25,"Drink"));
        trueList.add(9, new VendingMachineItem("C2", "Dr. Salt", 1.50, "Drink"));
        trueList.add(10, new VendingMachineItem("C3", "Mountain Melter", 1.50, "Drink"));
        trueList.add(11, new VendingMachineItem("C4", "Heavy", 1.50, "Drink"));
        trueList.add(12, new VendingMachineItem("D1", "U-Chews", 0.85, "Drink"));
        trueList.add(13, new VendingMachineItem("D2", "Little League Chew", 0.95, "Gum"));
        trueList.add(14, new VendingMachineItem("D3", "Chiclets", 0.75, "Gum"));
        trueList.add(15, new VendingMachineItem("D4", "Triplemint", 0.75, "Gum"));



        //Act
        List<VendingMachineItem> testList = test.makeItemList();


        //Assert
        Assert.assertEquals(testList.toString(), trueList.toString());

    }


}
