import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBankDB {
	
	public static void database() {
		final String DB_URL = "jdbc:derby:BankDB;create=true";

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			
			dropTables(conn);
			
			buildAccountTable(conn);
			
			conn.close();
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}

	public static void dropTables(Connection conn) {
		try {
			Statement stmt = conn.createStatement();

			try {
				stmt.execute("DROP TABLE Account");
				System.out.println("Account Table dropped");
			} catch (SQLException e) {

			}

		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Checking for existing tables.");
	}

	public static void buildAccountTable(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE Account(" + 
					"CardNum	Char(10)	NOT NULL, " + 
					"PassWord	Char(25)	NOT NULL, " +
					"UserName	Varchar(25)	NOT NULL, " + 
					"ChequeBanlance	Numeric(15,2)    NOT NULL, " + 
					"SavingBanlance	Numeric(15,2) " + 
					")");
			
	        stmt.execute("INSERT INTO Account VALUES ( " +
                    "'0123456789', " +
                    "'123456999', " +"'Abc Test1', " +
                    "10900,24000)" );
        
	        stmt.execute("INSERT INTO Account VALUES ( " +
                "'0345678912', " +
                "'987654999', " +"'Def Test2', " +
                "12900,14000)" );
         
	        stmt.execute("INSERT INTO Account VALUES ( " +
                  "'0654321987', " +
                  "'963852999', " +"'Hij Test3', " +
                  "22900,34000)" );
	        
	        stmt.execute("INSERT INTO Account VALUES ( " +
                "'0654321987', " +
                "'963852999', " +"'Lmn Test4', " +
                "22900,34000)" );
			
			System.out.println("Account table created");
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}

		

	}
	
	
	
}
