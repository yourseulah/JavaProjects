import java.util.ArrayList;

//Q2 - From Midterm - Shopping Application GUI version
public class Customer {
	String customerName;
	String membership;
	ArrayList<Product> shoppingList = new ArrayList<Product>();
	double moneyleft;
	
	public Customer(String customerName, String membership, double moneyleft) {
		this.customerName = customerName;
		this.membership = membership;
		this.moneyleft = moneyleft;
	}

}
