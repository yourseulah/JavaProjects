import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BankCustomer implements AccountManagement {
	final static String DB_URL = "jdbc:derby:BankDB";
	static String name ="";
	static String cardNum = "";
	static String passWord = "";
	static double ChequeBalance = 0; 
	static double SavingBalance = 0;
	static String custname = "";
	Boolean LoginStatus = false;
	static int count=0;
	ArrayList<String> lockacc = new ArrayList<String>();
	ArrayList<Integer> counter = new ArrayList<Integer>();
	
	public boolean CheckCardNum(String cnum) {
		boolean value = false;
		String cardnum = null;
		
		/*
		boolean check = true;
		String checklocknum;

	
		ReadLock.read();
		check = ReadLock.status;
		checklocknum = ReadLock.cardNum;
		if (checklocknum.contains(cnum) && check == false) {
			JOptionPane.showMessageDialog(
					null, "This Card is blocked!");
			value = false;
			System.exit(0);
		} else { */
			try {
				Connection conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(
						"SELECT * FROM Account WHERE CardNum = '" + cnum + "'");
				while (result.next()) {
					cardnum = result.getString("CardNum");
				}

				if (cardnum == null) {
					JOptionPane.showMessageDialog(null, "Wrong Number!");
				} else {
					JOptionPane.showMessageDialog(null, "Right Number!");
					value = true;
				}
				conn.close();

			} catch (SQLException e) {
			    System.out.println("ERROR: " + e.getMessage());
				e.printStackTrace();
			}
			return value;
		}
	//}

	@Override
	public boolean Login(String cnum, String pass) {
		boolean value = false;
		
		pass = pass + "999";
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					 "SELECT * FROM Account where CardNum = '"+cnum+
					 "' and PassWord = '"+pass+"'");
			while(result.next()) {
				cardNum = cnum;
				passWord = result.getString("PassWord");
				name = result.getString("UserName");
			}
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		
		if(name == null) {
			for(int i=0; i < counter.size(); i++) {
				if(lockacc.get(i) == cnum && counter.get(i)==3) {
					CreateLockFile.creatingFile(cnum, value);
					JOptionPane.showMessageDialog(null, "Card Blocked");
					System.exit(0);
				}
			}
			count++;
			lockacc.add(cnum);
			counter.add(count); 
			JOptionPane.showMessageDialog(null, "Wrong Password!");
		} else {
			ChequeBalance = GetChequeBalance(cnum);
			SavingBalance = GetSavingBalance(cnum);
			JOptionPane.showMessageDialog(null, 
			"Login Success! Your information is written in Log.txt file");
			try {
				FileCreate.createFile(name, cardNum, ChequeBalance, SavingBalance);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			value=true;
		}
		
		return value;
	}

	@Override
	public double GetChequeBalance(String cnum) {
		double cheqbalance = 0;
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					"SELECT * FROM Account where CardNum = '" + cnum + "'");
			while (result.next()) {
				cheqbalance = result.getDouble("ChequeBanlance");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		return cheqbalance;
	}

	@Override
	public double GetSavingBalance(String cnum) {
		double savingbalance = 0;
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					"SELECT * FROM Account where CardNum = '" + cnum + "'");
			while (result.next()) {
				savingbalance = result.getDouble("SavingBanlance");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}

		return savingbalance;
	}

}


