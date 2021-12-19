
package question;


public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	private int ID;
	private double talkingCharge, messageCost, networkCharge;
	private int discountRate;
	private int totalMessagesQuantity, totalTalkingTime;
	private double totalNetworkUsage;
	
	
	public Operator (int ID, double talkingCharge, double messageCost , double networkCharge, int discountRate) {
		this.ID=ID;
		this.talkingCharge=talkingCharge;
		this.messageCost=messageCost;
		this.networkCharge=networkCharge;
		this.discountRate=discountRate;
		this.totalMessagesQuantity=0;
		this.totalNetworkUsage=0;
		this.totalTalkingTime=0;
	}
	
	/** This method, calculates the talking cost for given operator.
	 * if the customer is older than 65 or younger than 18, then the operator makes a discount for a customer.
	 */
	public double calculateTalkingCost (int minute, Customer customer) {
			if (customer.getAge()>65 || customer.getAge()<18) {
				return ((double) minute*talkingCharge*(1-(double)discountRate*0.01));
			}
			else {
					return ((double) minute*talkingCharge);
			}
	}	
	
	
	/** This method calculates the message cost for given operator.
	 * if both customers are using the same operator, customer has discount on his/her messages.
	 */
	public double calculateMessageCost (int quantity, Customer customer, Customer other) {
		if (customer.getOperator() == other.getOperator()) {
			return ((double) quantity * messageCost * (1-(double) discountRate * 0.01));
		}
			else {
				return ((double) quantity * messageCost);
			}	
		}
		
	/**
	 * This method calculates the connection cost for given operator.
	 * @param amount  MB spent
	 * @return
	 */
	public double calculateNetworkCost (double amount) {
		return (amount * networkCharge);
	}
	
	public void timeAdd (int minute) {
		totalTalkingTime += minute;
	}
	public void messageAdd (int quantity) {
		totalMessagesQuantity += quantity;
	}
	public void networkAdd (double usage) {
		totalNetworkUsage += usage;
	}

	public int getTotalMessagesQuantity() {
		return totalMessagesQuantity;
	}

	public int getTotalTalkingTime() {
		return totalTalkingTime;
	}

	public double getTotalNetworkUsage() {
		return totalNetworkUsage;
	}
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

