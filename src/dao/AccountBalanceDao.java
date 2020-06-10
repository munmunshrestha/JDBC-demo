package dao;

import java.util.List;

import model.AccountBalance;
import model.AccountInfo;

//import model.AccountBalance;

public interface AccountBalanceDao {
	
	void createInitialBalance(int accountNum);
	void depositbalance(double amount, int accountNum);
	void withdrawBalance(double WithdrawAmount, int accountNum);
	AccountBalance getAccountBalance(int accountNum);

}
