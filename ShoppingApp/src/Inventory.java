
//Q2 - From Midterm - Shopping Application GUI version
public class Inventory {
	Product product;
	int stock;
	int discount;
	
	public Inventory(int productID, String productName, int stock, double price, int discount) {
		this.product = new Product(productID, productName, price, discount);
		this.stock = stock;
		this.discount = discount;
	}

	@Override
	public String toString() {
		return this.product.productName + ", " +
				this.stock + ", " +
				this.product.price + ", " +
				this.discount + "% off";
	}
	
	//Can create constructor with an object member!
}
