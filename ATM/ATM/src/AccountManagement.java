
public interface AccountManagement {
	double GetChequeBalance(String cnum);
	double GetSavingBalance(String cnum);
	boolean Login(String cnum, String pass); 
}
