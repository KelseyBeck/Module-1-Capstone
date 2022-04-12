package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {
    @Test
    public void makeItemListWorks() {
        //Arrange
        VendingMachine test = new VendingMachine();
        List<VendingMachineItem> trueList = new ArrayList<>();
        trueList.add(0, new VendingMachineItem("A1","Potato Crisps",3.05,"Chip"));
        trueList.add(1, new VendingMachineItem("A2", "Stackers", 1.45, "Chip"));
        trueList.add(2, new VendingMachineItem("A3", "Grain Waves", 2.75, "Chip"));


        //Act
        List<VendingMachineItem> testList = test.makeItemList();

        //Assert
        Assert.assertEquals(""+trueList.get(0).getItemName()+", "+trueList.get(1).getItemName()
                + ", " + trueList.get(2).getItemName(), ""+testList.get(0).getItemName()
                +", "+testList.get(1).getItemName()
                + ", " + testList.get(2).getItemName());

    }


}
