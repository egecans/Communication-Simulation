
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
	private int ID, age ,totalMessagesQuantity, totalTalkingTime;
	private String name;
	private Operator operator;
	private Bill bill;
	private double totalNetworkUsage;
	
	public Customer (int ID, int age, String name, Operator operator, Bill bill) {
		this.ID=ID;
		this.name=name;
		this.age=age;
		this.operator=operator;
		this.bill=bill;
		this.totalMessagesQuantity=0;
		this.totalNetworkUsage=0;
		this.totalTalkingTime=0;
	}
	
	
	/** This method is designed for talking operation. 
	 *  You should understand Operator and Bill classes' methods to understand these operations.
	 *  If given operation exceed the customer's limit or customer tries to phone himself, no actions occur.
	 * @param minute on phone
	 * @param other customer who is phoned
	 */
	public void talk (int minute, Customer other) {
		if ( bill.check((bill.getCurrentDebt() + operator.calculateTalkingCost(minute, this))) & (other != this)) {
			timeAdd(minute);
			other.timeAdd(minute);	
			operator.timeAdd(minute);
			other.operator.timeAdd(minute);
			bill.add(operator.calculateTalkingCost(minute, this));
		}
	}
	
	/** This method is designed for texting operation. 
	 *  You should understand Operator and Bill classes' methods to understand these operations.
	 *  If given operation exceed the customer's limit or customer tries to text himself, no actions occur.
	 * @param quantity of messages
	 * @param other customer who is texted
	 */
	public void message (int quantity, Customer other) {
		if (bill.check(bill.getCurrentDebt() + operator.calculateMessageCost(quantity, this, other )) & (other != this)) {
			messageAdd (quantity);
			operator.messageAdd(quantity);
			bill.add(operator.calculateMessageCost(quantity, this, other));
		}
	}
	
	/** This method is designed for texting operation. 
	 *  You should understand Operator and Bill classes' methods to understand these operations. 
	 *  If given operation exceed the customer's limit then no actions occur.
	 * @param amount
	 */
	public void connection (double amount) {
		if (bill.check(bill.getCurrentDebt() + operator.calculateNetworkCost(amount))) {
			networkAdd(amount);
			operator.networkAdd(amount);
			bill.add(operator.calculateNetworkCost(amount));
		}
	}
	
	/** This method is designed for the situation if costumer wants to change his operator.
	 * @param newOperator that customer change
	 */
	public void changeOperator (Operator newOperator) {
		this.operator = newOperator;
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
	
	public int getAge() {
		return age;
	}

	public int getTotalMessagesQuantity() {
		return totalMessagesQuantity;
	}


	public int getTotalTalkingTime() {
		return totalTalkingTime;
	}


	public String getName() {
		return name;
	}


	public double getTotalNetworkUsage() {
		return totalNetworkUsage;
	}



	public Operator getOperator() {
		return operator;
	}


	public Bill getBill() {
		return bill;
	}
	
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

