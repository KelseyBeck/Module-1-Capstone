package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {

   public void makeItemList(){
      List<VendingMachineItem> items = new ArrayList<VendingMachineItem>();
      File inputFile = new File("vendingmachine.csv");

       try {
           Scanner dataInput = new Scanner(inputFile);


           while (dataInput.hasNextLine()) {
               String lineOfInput = dataInput.nextLine();
               String [] values = lineOfInput.split("|");

               for (int i = 0; i < 16; i++) {
                   double price = Double.parseDouble(values[2]);
                   items.add(i, new VendingMachineItem(values[0],values[1],price,values[3]));
               }
           }



       } catch (FileNotFoundException e) {
           System.err.println(e.getMessage());
       }

      /* VendingMachineItem lays = new VendingMachineItem("Lays",3.30,"A1","chips");
       items.add(lays);
       VendingMachineItem doritos = new VendingMachineItem("Doritos",3.20,"A2","chips");
       items.add(doritos);
       VendingMachineItem cheetos = new VendingMachineItem("Cheetos",3.30, "A3", "chips");
       items.add(cheetos);
       VendingMachineItem skittles = new VendingMachineItem("Skittles",2.50,"B1","candy");
       items.add(skittles);
       VendingMachineItem mAndM = new VendingMachineItem("M&M", 2.60,"B2","candy");
       items.add(mAndM);
       VendingMachineItem snickers = new VendingMachineItem("Snickers",2.70, "B3", "candy");
       items.add(snickers);
       VendingMachineItem coke = new VendingMachineItem("Coka-Cola", 1.50, "C1","drink");
       items.add(coke);
       VendingMachineItem water = new VendingMachineItem("Water",1.00,"C2","drink");
       items.add(water);
       VendingMachineItem sprite = new VendingMachineItem("Sprite", 1.50, "C3","drink");
       items.add(sprite);
       VendingMachineItem spearmint = new VendingMachineItem("Spearmint", 0.25, "D1", "gum");
       items.add(spearmint);
       VendingMachineItem orbit = new VendingMachineItem("Orbit", 0.50,"D2","gum");
       items.add(orbit);
       VendingMachineItem bigRed = new VendingMachineItem("Big Red", 1.00,"D3","gum");
       items.add(bigRed);*/


   }







}