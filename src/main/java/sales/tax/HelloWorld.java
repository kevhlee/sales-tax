package sales.tax;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

class HelloWorld {

	public static void main(String[] args) {

		List<Item> items = new ArrayList<>();

		String input = null;
		int itemsPurchased = 0;
		double totalSalesTax = 0, totalCost = 0;
		boolean inputFlag = true;

		DecimalFormat df = new DecimalFormat("$0.00");

		// Create a buffered reader to handle the input from the user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Prompt user to enter how many items were purchased
		System.out.print("Please enter how many items were purchased: ");

		try {
			input = br.readLine();
			itemsPurchased = Integer.parseInt(input);
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

				// Input the item purchased
				System.out.print("What item did you purchase: ");

				String name = br.readLine();

				// Input how many were purchased
				System.out.print("How many did you purchase: ");

				input = br.readLine();
				int quantity = Integer.parseInt(input);

				// Input cost of item purchased
				System.out.print("How much did it cost: ");

				input = br.readLine();
				double price = Double.parseDouble(input);

				// Ask the user if it is a food item
				inputFlag = true;
				boolean exempt = false;

				while (inputFlag) {
					System.out.print("Is this a food, book, or medical item (Y or N): ");
					input = br.readLine();

					if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n")) {
						exempt = input.equals("Y") || input.equals("y");
						inputFlag = false;
					}
					else {
						System.out.print("Please enter 'Y' or 'N': ");
					}
				}

				// Ask the user if it is an imported item
				inputFlag = true;
				boolean imported = false;

				while (inputFlag) {
					System.out.print("Is this an imported item (Y or N): ");
					input = br.readLine();

					if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n")) {
						imported = input.equals("Y") || input.equals("y");
						inputFlag = false;
					}
					else {
						System.out.print("Please enter 'Y' or 'N': ");
					}
				}

				Item item = new Item(name, price, quantity, exempt, imported);

				items.add(item);

				// Find sales tax
				// Round up to the nearest 0.05

				totalSalesTax += item.getTaxAmount();
				totalCost += item.getTotalPriceWithTax();
			}
		}
		catch (IOException e) {
			System.out.println("IO error trying to read what item was purchased.");
			System.exit(1);
		}

		System.out.print("\n" + "Receipt Details:" + "\n");
		for (Item item : items) {
			System.out.print(item.getQuantity() + " " + item.getName() + ": " + df.format(item.getTotalPriceWithTax()) + "\n");
		}
		System.out.print("Sales Taxes: " + df.format(totalSalesTax) + "\n");
		System.out.print("Total: " + df.format(totalCost) + "\n");
	}
}
