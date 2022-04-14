package com.techelevator;

import com.techelevator.view.Menu;

import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private static final String FEED_MONEY_1 = "1.00";
	private static final String FEED_MONEY_2 = "2.00";
	private static final String FEED_MONEY_5 = "5.00";
	private static final String FEED_MONEY_10 = "10.00";
	private static final String FEED_MONEY_DONE = "Finish feeding money";
	private static final String[] FEED_MONEY_OPTIONS = {FEED_MONEY_1, FEED_MONEY_2, FEED_MONEY_5, FEED_MONEY_10, FEED_MONEY_DONE};

	private double currentMoney = 0.00;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String mainChoice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingMachine vendor = new VendingMachine();
			vendor.makeItemList();
			// List<VendingMachineItem> itemList = vendor.makeItemList();

			if (mainChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				//todo: make this a vending machine method so it can be called in purchase method
				//todo: replace with vendor.displayItems();
				vendor.displayItems();
				/*
				for(int i = 0; i<itemList.size(); i++) {
					System.out.println("("+(i+1)+")"+itemList.get(i).toString());
				}
				*/

			} else if (mainChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean inPurchaseMenu = true;
				while (inPurchaseMenu) {
					//todo: make while loop like while (inPurchaseMenu)
					//todo: make finish option inPurchaseMenu = false after giving change

					System.out.println("Current Money Provided: $" + currentMoney);
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						// menu stays in feed money until "Finish feeding money" is selected
						boolean feedMoreMoney = true;
						while (feedMoreMoney) {
							System.out.println("Please choose a dollar amount to feed into the machine:");
							System.out.println("(Current Money Provided: $" + currentMoney + ")");

							String feedChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
							//got rid of ParseDouble, just added the dollar amount based on choice

							if (feedChoice.equals(FEED_MONEY_1)) {
								currentMoney += 1;
							} else if (feedChoice.equals(FEED_MONEY_2)) {
								currentMoney += 2;
							} else if (feedChoice.equals(FEED_MONEY_5)) {
								currentMoney += 5;
							} else if (feedChoice.equals(FEED_MONEY_10)) {
								currentMoney += 10;
							} else if (feedChoice.equals(FEED_MONEY_DONE)) {
								//returns customer to previous menu
								feedMoreMoney = false;
							}

						}

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						//create menu from list of items for customer to choose
						String[] productOptions = new String[vendor.items.size()];
						for (int i = 0; i < productOptions.length; i++) {
							productOptions[i] = vendor.items.get(i).toString();
						}
						String itemChoice = (String) menu.getChoiceFromOptions(productOptions);

						for (int i = 0; i < vendor.items.size(); i++) {
							if (itemChoice.startsWith(vendor.items.get(i).getSlot())) {

								if (vendor.items.get(i).getQuantity() == 0) {
									System.out.println("Item is sold out. Please select another.");

								} else if (currentMoney < vendor.items.get(i).getPrice()) {
									System.out.println("Insufficient funds.");

								} else {
									//subtract item quantity by 1
									vendor.items.get(i).setQuantity(vendor.items.get(i).getQuantity()-1);

									//subtract item cost from current funds
									currentMoney -= vendor.items.get(i).getPrice();

									//print name, cost, and money remaining
									System.out.println(vendor.items.get(i).toString());
									vendor.items.get(i).printFlavorText();
								}
							}
						}



					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						//todo: give change
						int quarters = 0;
						int dimes = 0;
						int nickels = 0;
						double change = currentMoney;
						while (currentMoney >= 0.25) {
							currentMoney -= 0.25;
							quarters++;
						}
						while (currentMoney >= 0.1) {
							currentMoney -= 0.1;
							dimes++;
						}
						while (currentMoney >= 0.05) {
							currentMoney -= 0.05;
							nickels++;
						}
						System.out.println("Thank you, your change is $" + change+": "
								+ quarters+" quarters, "+ dimes + " dimes, and " + nickels + " nickels");


						inPurchaseMenu = false;
					}
				}



			} else if (mainChoice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do stuff
			}
		}
	}



	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
