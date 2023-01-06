import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Q2 - From Midterm - Shopping Application GUI version
public class Shopping extends JFrame implements ActionListener  {

	JPanel myPanel = new JPanel();
	JLabel lblGuide;
	JLabel lblProcedure;
	JLabel lblInventory = new JLabel("");
	JLabel lblWarning = new JLabel("");
	JTextField tfuserInput;
	Font guideFont = new Font("Calibri", Font.BOLD, 16);
	Font displayFont = new Font("Calibri", Font.BOLD, 14);
	JButton Click = new JButton("Click");
	
	Inventory[] inv = new Inventory[3];
	Customer cus = new Customer("Seu", "Gold", 10.5);
	
	public Shopping () {
		
		inv[0] = new Inventory(1, "Egg", 10, 4.99, 10);
		inv[1] = new Inventory(2, "Apple", 1, 2.99, 20);
		inv[2] = new Inventory(3, "Toothpaste", 3, 1.99, 50);
		
		myPanel.setLayout(null);
		
		lblGuide = new JLabel("Pls enter number of the following selections : ");
		lblGuide.setFont(guideFont);
		lblGuide.setBounds(120, 50, 400, 20);
		
		lblProcedure = new JLabel("<html>(1) Display the inventory<br>"
				+ "(2) Add item to your shopping list<br>"
				+ "(3) Delete item from your shopping list<br>"
				+ "(4) Checkout<br>"
				+ "(5) Quit </html>");
		lblProcedure.setFont(displayFont);
		lblProcedure.setBounds(120, 85, 400, 100);
		
		tfuserInput = new JTextField(10);  //create an object!!
		tfuserInput.setBounds(120, 210, 100, 35);
		Click.setBounds(240, 210, 100, 35);
		
		lblInventory = new JLabel();
		lblInventory.setBounds(120, 260, 400, 80);
		lblInventory.setFont(displayFont);
		
		myPanel.add(lblGuide);
		myPanel.add(lblProcedure);
		myPanel.add(lblInventory);
		myPanel.add(lblWarning);
		myPanel.add(tfuserInput);
		myPanel.add(Click);
		
		this.getContentPane().add(myPanel);
		this.setVisible(true);
		

		Click.addActionListener(this);
	}
	
	public static void main(String[] args) {
		Shopping s = new Shopping();

		final int WIDTH = 600;
		final int HEIGHT = 500;
		s.setSize(WIDTH, HEIGHT);
		s.setTitle("Shopping App");
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int inputNum = Integer.parseInt(tfuserInput.getText());
		if (e.getSource() == Click) { // don't forget action, e.getSource!!!
			// System.out.println(inputNum);
			if (inputNum < 1 || inputNum > 5) {
				String warning = "Invalid Selection! Pls enter from 1 to 5 !";
				lblWarning.setText(warning);
				lblWarning.setBounds(120, 260, 400, 30);
				lblWarning.setForeground(Color.red);
				tfuserInput.setText(" ");
			} else {
				switch (inputNum) {
				case 1:
					String d = DisplayInventory(inv);
					lblInventory.setText(d);
					// System.out.println(DisplayInventory(inv));
					break;
				case 2:
					AddItem(inv, cus);
					break;
				case 3:
					DeleteItem(inv, cus);
					break;
				case 4:
					Checkout(inv, cus);
					break;
				case 5:
					Quit(inv, cus);
					break;
				}
			}
		}
	}
	
	public String DisplayInventory(Inventory[] inv) {
		String d = "";
		String s = "";
		for (int i=0; i < inv.length; i++) {
			s += "(" + (i+1) + ") " + inv[i].toString() + "<br>";
			//s+= inv[i] + "\n";
		}
		
		System.out.println(s);
		d = "<html> List of Inventory "
			+ "(name, stock, price, discount) : <br> " 
			+ s;
		//System.out.println(d);
		return d;
	}

	public void AddItem(Inventory[] inv, Customer cus) {
		String a = JOptionPane.showInputDialog(null, "Pls input the index of the item you want to add:");
		int inputNum = Integer.parseInt(a);
		
		if (inputNum < 1 || inputNum > 3) {
			String warning = 
					"Invalied Selection! Enter a number among below \n"
					+ "(1) Egg \n"
					+ "(2) Apple \n"
					+ "(3) Toothpaste";
			JOptionPane.showMessageDialog(null, warning); 
		}
		
		switch(inputNum) {
		case 1:
			AddItemInv(inv[0], cus);
			break;
		case 2:
			AddItemInv(inv[1], cus);
			break;
		case 3:
			AddItemInv(inv[2], cus);
			break;
		} 
	}
	
	public void AddItemInv(Inventory inv, Customer cus) {
		if (inv.stock > 0) {
			JOptionPane.showMessageDialog(null,
					"You selected " + inv.product.productName + ". \n" 
							+ inv.product.productName
							+ " will be added to your shopping list. \n" 
							);
			cus.shoppingList.add(inv.product);
			inv.stock -= 1;

			String a = "";
			for (int i = 0; i < cus.shoppingList.size(); i++) {
				a += "<html> (" + cus.shoppingList.get(i).productID + ") " 
						+ cus.shoppingList.get(i).productName + ", "
						+ cus.shoppingList.get(i).price + ", " 
						+ cus.shoppingList.get(i).discount + "% off <br>";
			}
			JOptionPane.showMessageDialog(null, 
					"Your updated shopping list is : \n" + a);

		} else {
			JOptionPane.showMessageDialog(null,
					"You selected " + inv.product.productName 
					+ ". \n Oops! NO more stock!");
		}
	}
	
	public void DeleteItem(Inventory[] inv, Customer cus) {
		String d = "";
		for (int i = 0; i < cus.shoppingList.size(); i++) {
			d += "<html> (" + (i+1) + ") " 
					+ cus.shoppingList.get(i).productName + ", "
					+ cus.shoppingList.get(i).price + ", " 
					+ cus.shoppingList.get(i).discount + "% off <br>";
		}
		String a = JOptionPane.showInputDialog(null, 
				"Here is your shopping list : \n" + d 
				+ "\n Which one you want to delete?");
		int inputNum = Integer.parseInt(a);
		//System.out.println(inputNum);
		inv[cus.shoppingList.get(inputNum).productID-1].stock++;
		cus.shoppingList.remove(inputNum-1);
		//The last number doesn't work IDK
		
		d = "";
		for (int i = 0; i < cus.shoppingList.size(); i++) {
			d += "<html> (" + (i+1) + ") " 
					+ cus.shoppingList.get(i).productName + ", "
					+ cus.shoppingList.get(i).price + ", " 
					+ cus.shoppingList.get(i).discount + "% off <br>";
		}
		
		JOptionPane.showMessageDialog(null, 
				"Ok, item " + inputNum + " is deleted. \n"
				+ "Here is your updated shopping list: \n"
				+ d); 
	}
	
	public void Checkout(Inventory[] inv, Customer cus) {
		double total = 0.0;
		for (int i=0; i < cus.shoppingList.size(); i++) {
			total += (cus.shoppingList.get(i).price*
					(1-cus.shoppingList.get(i).discount/100.0));
			//System.out.println("The discounted rate : " + (1-((cus.shoppingList.get(i).discount)/100.0)));
		}
		
		if(total <= cus.moneyleft) {
			cus.moneyleft -= total;
			String t = "Your total (discount is applied) is $" + String.format("%.2f", total) + "\n"
					+ "Your new balance is $" + String.format("%.2f", cus.moneyleft)+ "\n"
					+ "Thank you for shopping with us :)";
			JOptionPane.showMessageDialog(null, t);
			cus.shoppingList.clear();
			
		} else {
			String t = "Your total (discount is applied) is $" + String.format("%.2f", total) + "\n"
					+ "Your current balance is $" + String.format("%.2f", cus.moneyleft)+ "\n"
					+ "Your balance is not enough :( ";
			JOptionPane.showMessageDialog(null, t);
			
			int max = (int)cus.shoppingList.get(0).price;
			int index = 0;
			for(int i=1; i < cus.shoppingList.size(); i++) {
				if(max < cus.shoppingList.get(i).price) {
					max = (int) cus.shoppingList.get(i).price;
					index = i;
				}
			}
			String r = "The following item is removed from your shopping list: \n"
					+ "(" + (index+1) + ")" + cus.shoppingList.get(index).productName +
					", " + cus.shoppingList.get(index).price;
			JOptionPane.showMessageDialog(null, r);
			inv[cus.shoppingList.get(index).productID-1].stock++;
			cus.shoppingList.remove(index);
			
			total=0.0;
			for (int i=0; i < cus.shoppingList.size(); i++) {
				total += (cus.shoppingList.get(i).price
						*(1-cus.shoppingList.get(i).discount/100.0));
			}
			if (total <= cus.moneyleft) {
				cus.moneyleft -= total;
				t = "";
				t = "Your total (discount is applied) is $" + String.format("%.2f", total) + "\n"
					+ "Your new balance is $" + String.format("%.2f", cus.moneyleft)+ "\n"
					+ "Thank you for shopping with us :)";
				JOptionPane.showMessageDialog(null, t);
				cus.shoppingList.clear();
				
				//Output to the File ------------------------
				CreateFile file = new CreateFile();
				try {
					file.createFile(t);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// -------------------------------------------
				
			} else {
				t = "";
				t = "Your total (discount is applied) is $" + String.format("%.2f", total) + "\n"
					+ "Your current balance is $" + String.format("%.2f", cus.moneyleft)+ "\n"
					+ "Your balance is not enough :( ";
				JOptionPane.showMessageDialog(null, t);
			}
		}
	}
	
	public void Quit(Inventory[] inv, Customer cus) {
		JOptionPane.showMessageDialog(null,
				"You didn't buy anything and your balance is $" 
				+ String.format("%.2f", cus.moneyleft) + 
				". \nSee you next time!");
		this.dispose();
	}
}
