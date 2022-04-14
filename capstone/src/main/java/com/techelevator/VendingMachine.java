package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public List<VendingMachineItem> items = new ArrayList<>();

    public List<VendingMachineItem> makeItemList() {
        File inputFile = new File("vendingmachine.csv");

        try (Scanner dataInput = new Scanner(inputFile)) {

            int e = 0; // e = iterator for while loop below
            while (dataInput.hasNextLine()) {
                //for each loop, make values[] from the line
                String lineOfInput = dataInput.nextLine();
                String[] values = lineOfInput.split("\\|");

               /* get constructor arguments from values[] for the new
               VendingMachineItem added to the list at index e */
                double price = Double.parseDouble(values[2]);
                items.add(e, new VendingMachineItem(values[0], values[1], price, values[3]));
                e++;
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return items;
    }

    public void displayItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("(" + (i + 1) + ")" + items.get(i).toString());
        }
    }
}
