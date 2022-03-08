import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

class HelloWorld
{
	public static final double SALES_TAX = 0.10;
	public static final double IMPORT_TAX = 0.05;

	public static void main (String[] args) 
	{

		String[] items = new String[5];
		int[] amount = new int[5];
		double[] cost = new double[5];
		String[] exempt = new String[5];
		String[] imported = new String[5];
		double[] salesTax = new double[5];
		double[] taxedCost = new double[5];

		String input = null;
		int itemsPurchased = 0;
		double unroundedSalesTax = 0, unroundedImportTax = 0, totalSalesTax = 0, totalCost = 0;
		boolean inputFlag = true;

		DecimalFormat df = new DecimalFormat("$0.00");

		// Create a buffered reader to handle the input from the user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Prompt user to enter how many items were purchased
		System.out.print("Please enter how many items were purchased: ");
			try
			{
				input = br.readLine();
				itemsPurchased = Integer.parseInt(input);
			} 
			catch (IOException e) 
			{
				System.out.println("IO error trying to read how many items purchased.");
				System.exit(1);
			}


		// For loop to read through all items purchased
		// Take input from the user for item name, amount, and cost

		for (int i = 0; i < itemsPurchased; i++)
		{
			System.out.print("\n" + "Item #" + (i + 1) + "\n");
			// Input the item purchased
			System.out.print("What item did you purchase: ");
				try
				{
					items[i] = br.readLine();
				} 
				catch (IOException e) 
				{
					System.out.println("IO error trying to read what item was purchased.");
					System.exit(1);
				}

			// Input how many were purchased
			System.out.print("How many did you purchase: ");
				try
				{
					input = br.readLine();
					amount[i] = Integer.parseInt(input);
				} 
				catch (IOException e) 
				{
					System.out.println("IO error trying to read what item was purchased.");
					System.exit(1);
				}

			// Input cost of item purchased
			System.out.print("How much did it cost: ");
				try
				{
					input = br.readLine();
					cost[i] = Double.parseDouble(input);
					cost[i] = (cost[i] * amount[i]);
				} 
				catch (IOException e) 
				{
					System.out.println("IO error trying to read what item was purchased.");
					System.exit(1);
				}

			// Ask the user if it is a food item
				try
				{
					inputFlag = true;

					while (inputFlag)
					{
						System.out.print("Is this a food, book, or medical item (Y or N): ");
						input = br.readLine();
						if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n"))
						{
							exempt[i] = input;
							inputFlag = false;
						}
						else
						{
							System.out.print("Please enter 'Y' or 'N': ");
						}
					}
				} 
				catch (IOException e) 
				{
					System.out.println("IO error trying to read what item was purchased.");
					System.exit(1);
				}

			// Ask the user if it is an imported item
				try
				{
					inputFlag = true;
					while (inputFlag)
					{
						System.out.print("Is this an imported item (Y or N): ");
						input = br.readLine();
						if (input.equals("Y") || input.equals("y") || input.equals("N") || input.equals("n"))
						{
							imported[i] = input;
							inputFlag = false;
						}
						else
						{
							System.out.print("Please enter 'Y' or 'N': ");
						}
					}
				} 
				catch (IOException e) 
				{
					System.out.println("IO error trying to read what item was purchased.");
					System.exit(1);
				}

			// Find sales tax
			// Round up to the nearest 0.05

			if (exempt[i].equals("N") || exempt[i].equals("n"))
			{
				// Multiply by .1
				// Add to total sales tax
				unroundedSalesTax = (cost[i] * SALES_TAX);
				salesTax[i] = salesTax[i] + ((Math.ceil(unroundedSalesTax*20))/20);

				/* Output to check for errors
				System.out.println("Unrounded Sales Tax is: " + unroundedSalesTax);
				System.out.println("Sales Tax is: " + salesTax[i]);*/
			}

			if (imported[i].equals("Y") || imported[i].equals("y"))
			{
				// Multiply by .05
				// Add to total sales tax
				// Change member to reflect sales tax
				unroundedImportTax = (cost[i] * IMPORT_TAX);
				salesTax[i] += ((Math.ceil(unroundedImportTax*20))/20);

				/* Output to check for errors
				System.out.println("Unrounded Import Tax is: " + unroundedImportTax);
				System.out.println("Sales Tax is: " + salesTax[i]);*/
			}

			totalSalesTax += salesTax[i];
			taxedCost[i] = cost[i] + salesTax[i];
		}

		
		for (int i = 0; i < itemsPurchased; i++)
		{
			totalCost += taxedCost[i];
		}

		System.out.print("\n" + "Receipt Details:" + "\n");
		for (int i = 0; i < itemsPurchased; i++)
		{
			System.out.print(amount[i] + " " + items[i] + ": " + df.format(taxedCost[i]) + "\n");
		}
		System.out.print("Sales Taxes: " + df.format(totalSalesTax) + "\n");
		System.out.print("Total: " + df.format(totalCost) + "\n");
	}
}
