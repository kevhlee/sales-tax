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

		int itemsPurchased = 0;

		// Create a buffered reader to handle the input from the user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Prompt user to enter how many items were purchased
		try {
			itemsPurchased = promptInteger(br, "Please enter how many items were purchased: ");
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

				String name = promptInput(br, "What item did you purchase: ");
				int quantity = promptInteger(br, "How many did you purchase: ");
				double price = promptDouble(br, "How much did it cost: ");
				boolean exempt = promptYesOrNo(br, "Is this a food, book, or medical item (Y or N): ");
				boolean imported = promptYesOrNo(br, "Is this an imported item (Y or N): ");

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

	private static String promptInput(BufferedReader bufferedReader, String prompt) throws IOException {
		System.out.print(prompt);
		return bufferedReader.readLine();
	}

	private static int promptInteger(BufferedReader bufferedReader, String prompt) throws IOException {
		return Integer.parseInt(promptInput(bufferedReader, prompt));
	}

	private static double promptDouble(BufferedReader bufferedReader, String prompt) throws IOException {
		return Double.parseDouble(promptInput(bufferedReader, prompt));
	}

	private static boolean promptYesOrNo(BufferedReader bufferedReader, String prompt) throws IOException {
		while (true) {
			String input = promptInput(bufferedReader, prompt);

			if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n")) {
				return input.equals("Y") || input.equals("y");
			}
			else {
				System.out.print("Please enter 'Y' or 'N': ");
			}
		}
	}
}
