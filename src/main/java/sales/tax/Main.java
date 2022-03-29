package sales.tax;

import java.io.*;
import java.util.*;
import java.text.*;

class Main {

	public static void main(String[] args) {
		Order order = createOrder();

		System.out.println(order.generateReceipt());
	}

	private static Order createOrder() {
		Order order = new Order();

		UserInputReader userInputReader = new UserInputReader(System.in, System.out);
		int itemsPurchased = 0;

		// Prompt user to enter how many items were purchased
		try {
			itemsPurchased = userInputReader.promptInteger("Please enter how many items were purchased: ");
		}
		catch (IOException e) {
			System.out.println("IO error trying to read how many items purchased.");
			System.exit(1);
		}

		// For loop to read through all items purchased
		// Take input from the user for item name, amount, and cost

		try {
			for (int i = 0; i < itemsPurchased; i++) {
				System.out.print("\n" + "sales.tax.Item #" + (i + 1) + "\n");

				String name = userInputReader.promptInput("What item did you purchase: ");
				int quantity = userInputReader.promptInteger("How many did you purchase: ");
				double price = userInputReader.promptDouble("How much did it cost: ");
				boolean exempt = userInputReader.promptYesOrNo("Is this a food, book, or medical item (Y or N): ");
				boolean imported = userInputReader.promptYesOrNo("Is this an imported item (Y or N): ");

				Item item = new Item(name, price, quantity, exempt, imported);

				order.addItem(item);
			}
		}
		catch (IOException e) {
			System.out.println("IO error trying to read what item was purchased.");
			System.exit(1);
		}

		return order;
	}

}
