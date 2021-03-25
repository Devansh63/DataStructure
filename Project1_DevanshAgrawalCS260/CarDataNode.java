package Project1_DevanshAgrawalCS260;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarDataNode {

	private String data;
	private CarDataNode next;
	private CarDataNode prev; // Creating the Nodes
	private int number;
	private Date checkin = new Date();
	private Date checkout;

	public CarDataNode(String data, int i, CarDataNode next, CarDataNode prev) // This is the Constructor
	{
		this.number = i;
		this.prev = prev;
		this.next = next;
		this.data = data;
	}

	public CarDataNode addNodeAfter(String data, int i) { // Used to add a node to the caller.
		return next = new CarDataNode(data, i, this, next);
	}

	public int getIndex() {
		return number;
	}

	public void setIndex(int i) {
		this.number = i;
	}

	public String toString() { // Used to Print out the Node.
		if (checkout == null) {
			String print;
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// LocalDateTime now = LocalDateTime.now();
			print = "Number " + number + " The license plate of entered vehicle is: " + data
					+ "\nThe check in time is: " + dtf.format(checkin) + "\n";
			return print;
		} else {
			String print;
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// LocalDateTime now = LocalDateTime.now();
			print = "Number " + number + "The license plate of entered vehicle is: " + data
					+ "\nThe check out time is: " + dtf.format(checkout) + "\n";
			return print;
		}
	}

	public CarDataNode removeNode() { // Using it to Remove the called node
//		if(this.head == null)
//		{
//			return;
//		}
//		else if(prev == null) {
//			head =next;
//		}
//		else if ( tail == null) {
//			
//		}
		prev.next = next;
		next.prev = prev;
		return next;
	}

	public void checkIn() {
		checkin = new Date();
	}

	public void checkOut() {
		checkout = new Date();
	}

	public boolean equals(CarDataNode node) { // To check if the Strings are equal
		if (data.equals(node.data)) {
			return true;
		}
		return false;
	}

	public static CarDataNode searchFromNode(CarDataNode initialNode, String initialString) { // To search for Nodes
																								// based on string

		while (initialNode != null) {
			if (initialNode.data.equals(initialString)) {
				return initialNode;
			}
			initialNode = initialNode.getNext();
		}
		return null;
	}

	public CarDataNode searchFromIndexNode(CarDataNode initialNode, int i) { // To search for Nodes based on index
																				// number

		while (initialNode != null) {
			if (initialNode.number == i)
				return initialNode;
			initialNode = initialNode.next;

		}
		return null;
	}

	public String getData() { // Auto Generated Getters and Setters
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CarDataNode getNext() {
		return next;
	}

	public void setNext(CarDataNode next) {
		this.next = next;
	}

	public CarDataNode getPrev() {
		return prev;
	}

	public void setPrev(CarDataNode prev) {
		this.prev = prev;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

}
