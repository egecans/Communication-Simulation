
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		

		
		int customerCount = 0;
		int operatorCount = 0;
		
		/**
		 * The aim of this for loop is read and execute the inputfile in the way of given commands
		 */
		for (int i = 0; i<N+1; i++) {
			
			String line = reader.nextLine().trim();
			
			/**
			 * Creating a customer with given parameters.
			 */
			if (line.startsWith("1")) {
				String split[] = line.split(" ", 5);
				String name = split[1];
				int age = Integer.parseInt(split[2]);
				int operatorID = Integer.parseInt(split[3]);
				double limitingAmount = Double.parseDouble(split[4]);
				Bill bill = new Bill(limitingAmount);
				Customer customer = new Customer(customerCount, age, name, operators[operatorID], bill);
				customers[customerCount] = customer;
				customerCount += 1;					
			}
			/**
			 * Creating an operator with given parameters.
			 */
			if (line.startsWith("2")) {
				String split[] = line.split(" ", 5);
				double talkingCharge = Double.parseDouble(split[1]);
				double messageCost = Double.parseDouble(split[2]);
				double networkCharge = Double.parseDouble(split[3]);
				int discountRate = Integer.parseInt(split[4]);
				Operator operator = new Operator(operatorCount, talkingCharge, messageCost, networkCharge, discountRate);
				operators[operatorCount] = operator;
				operatorCount += 1;			
			}
			
			/**
			 * Talking operation with given parameters
			 */
			if (line.startsWith("3")) {
				String split[] = line.split(" ", 4);
				int firstCustomerID = Integer.parseInt(split[1]);
				int secondCustomerID = Integer.parseInt(split[2]);
				int talkingTime = Integer.parseInt(split[3]);
				customers[firstCustomerID].talk(talkingTime, customers[secondCustomerID]);
			}
			
			/**
			 * Texting operation with given parameters
			 */
			if (line.startsWith("4")) {
				String split[] = line.split(" ", 4);
				int firstCustomerID = Integer.parseInt(split[1]);
				int secondCustomerID = Integer.parseInt(split[2]);
				int quantity = Integer.parseInt(split[3]);
				customers[firstCustomerID].message(quantity, customers[secondCustomerID]);
			}
			
			/**
			 * Connecting operation with given parameters
			 */
			if (line.startsWith("5")) {
				String split[] = line.split(" ", 3);
				int customerID = Integer.parseInt(split[1]);
				double amountMB = Double.parseDouble(split[2]);
				customers[customerID].connection(amountMB);
			}
			
			/**
			 * Paying operation with given parameters
			 */
			if (line.startsWith("6")) {
				String split[] = line.split(" ", 3);
				int customerID = Integer.parseInt(split[1]);
				double amountpaid = Double.parseDouble(split[2]);
				customers[customerID].getBill().pay(amountpaid);
			}
			
			/**
			 * Changing operator
			 */
			if (line.startsWith("7")) {
				String split[] = line.split(" ", 3);
				int customerID = Integer.parseInt(split[1]);
				int operatorID = Integer.parseInt(split[2]);
				customers[customerID].changeOperator(operators[operatorID]);
			}
			
			/**
			 * Changing limit
			 */
			if (line.startsWith("8")) {
				String split[] = line.split(" ", 3);
				int customerID = Integer.parseInt(split[1]);
				double newLimit = Double.parseDouble(split[2]);
				customers[customerID].getBill().changeTheLimit(newLimit);
			}		
		}
		
		int mostTalkingVar = -1;
		int mostTextingVar = -1;
		double mostConnectingVar = -0.1;
		int mostTalkingID = 0;
		int mostTextingID = 0;
		int mostConnectingID = 0;
			
		/**
		 * This loop is for printing operators' information to output.
		 */
		for (int i = 0; i<O ; i++) {
			String opLine = "Operator " + i + " : " + operators[i].getTotalTalkingTime() + " " + operators[i].getTotalMessagesQuantity() +" " + String.format("%.2f", operators[i].getTotalNetworkUsage() );	
			outstream1.println(opLine);
		}
		
		/**
		 * This loop is both to print customers' information and identify customer who talks, connects, messages most.
		 */
		for (int i=0; i<C; i++) {	
			String custLine = "Customer " + i + " : " + String.format("%.2f", customers[i].getBill().getTotalMoneySpent())+ " " + String.format("%.2f",customers[i].getBill().getCurrentDebt());
			outstream1.println(custLine);
	
			if (customers[i].getTotalTalkingTime() > mostTalkingVar) {
				mostTalkingVar = customers[i].getTotalTalkingTime();
				mostTalkingID = i;
			}
			if (customers[i].getTotalMessagesQuantity() > mostTextingVar) {
				mostTextingVar = customers[i].getTotalMessagesQuantity();
				mostTextingID = i;
			}
			if (customers[i].getTotalNetworkUsage() > mostConnectingVar) {
				mostConnectingVar = customers[i].getTotalNetworkUsage();
				mostConnectingID = i;	
				}
		}
		
		outstream1.println(customers[mostTalkingID].getName() + " : " + mostTalkingVar);
		outstream1.println(customers[mostTextingID].getName() + " : " + mostTextingVar);
		outstream1.println(customers[mostConnectingID].getName() + " : " + String.format("%.2f", mostConnectingVar));

		
		


		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}