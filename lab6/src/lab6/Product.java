package lab6;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;


public class Product implements Serializable {
	private String productName;
	private int amount;
	private String description;
	
//	public static Scanner scanner = new Scanner(System.in);
	 
	public Product() {
		
	}
	
	public Product(String productName, int amount, String description) {
	
		this.productName=productName;
		this.amount= amount;
		this.description=description;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
