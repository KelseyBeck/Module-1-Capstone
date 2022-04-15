package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineItemTest {
    @Test
    public void toStringWorks() {
        //Arrange
        VendingMachineItem chip = new VendingMachineItem("A1","Lays",2.50,"Chip");

        //Act
        String testString = chip.toString();
        String trueString = "A1, Lays, $2.5, Quantity: 5";

        //Assert
        Assert.assertEquals(trueString, testString);
    }

}
