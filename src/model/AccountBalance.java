package model;

public class AccountBalance {
	private double depositAmount;
	private double withdrawAmount;
	private double balance;
	private int accountInfoId;

	
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	public double getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getAccountInfoId() {
		return accountInfoId;
	}
	public void setAccountInfoId(int accountInfoId) {
		this.accountInfoId = accountInfoId;
	}

}
