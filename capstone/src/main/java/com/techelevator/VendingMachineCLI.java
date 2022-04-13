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
	private static final String[] FEED_MONEY_OPTIONS = {FEED_MONEY_1, FEED_MONEY_2, FEED_MONEY_5, FEED_MONEY_10};


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String mainChoice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingMachine vendor = new VendingMachine();
			List<VendingMachineItem> itemList = vendor.makeItemList();

			if (mainChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				for(int i = 0; i<itemList.size(); i++) {
					System.out.println("("+(i+1)+")"+itemList.get(i).toString());
				}

			} else if (mainChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//find how to update current money in menu
				double currentMoney = 0.00;
				System.out.println("Current Money Provided: " + currentMoney);
				String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					String feedChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
					double feedChoiceDouble = Double.parseDouble(feedChoice);

					// not updating currentMoney right now, needs fix
					if (feedChoiceDouble == 1) {
						currentMoney += 1;
					} else if (feedChoiceDouble == 2) {
						currentMoney += 2;
					} else if (feedChoiceDouble == 3) {
						currentMoney += 5;
					} else if (feedChoiceDouble == 4) {
						currentMoney += 10;
					}



				} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

				} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

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
