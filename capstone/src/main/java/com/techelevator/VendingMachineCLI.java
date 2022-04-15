package com.techelevator;

import com.techelevator.view.Menu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	// added menu options instead of using scanners to limit user error and keep everything uniform
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
	private double pastMoney = 0.00; //used for log file, set to current money right before a transaction.
	private static final DecimalFormat moneyFormat = new DecimalFormat("#0.00"); //prints doubles nicer

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
				vendor.displayItems();

			} else if (mainChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean inPurchaseMenu = true;
				while (inPurchaseMenu) {

					System.out.println("Current Money Provided: $" + moneyFormat.format(currentMoney));
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

						// menu stays in feed money until "Finish feeding money" is selected
						boolean feedMoreMoney = true;

						while (feedMoreMoney) {
							System.out.println("Please choose a dollar amount to feed into the machine:");
							System.out.println("(Current Money Provided: $" + moneyFormat.format(currentMoney) + ")");

							String feedChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
							pastMoney = currentMoney;

							if (feedChoice.equals(FEED_MONEY_1)) {
								currentMoney += 1;
								log("FEED:");
							} else if (feedChoice.equals(FEED_MONEY_2)) {
								currentMoney += 2;
								log("FEED:");
							} else if (feedChoice.equals(FEED_MONEY_5)) {
								currentMoney += 5;
								log("FEED:");
							} else if (feedChoice.equals(FEED_MONEY_10)) {
								currentMoney += 10;
								log("FEED:");
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
						pastMoney = currentMoney;

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

									log(vendor.items.get(i).getItemName() + " " + vendor.items.get(i).getSlot());
								}
							}
						}



					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						pastMoney = currentMoney;
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
						currentMoney = 0.00; //for possible double carryover
						System.out.println("Thank you, your change is $" + moneyFormat.format(change) +": "
								+ quarters+" quarters, "+ dimes + " dimes, and " + nickels + " nickels");

						log("GIVE CHANGE: ");
						inPurchaseMenu = false;
					}
				}



			} else if (mainChoice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do stuff
			}
		}
	}

	public void log(String message) {
		String logMsg = message + " $"+moneyFormat.format(pastMoney)+" $"+moneyFormat.format(currentMoney);
		VMLog.log(logMsg);
	}



	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
