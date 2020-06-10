package client;

import java.util.List;
import java.util.Scanner;

import dao.AccountBalanceDao;
import dao.AccountBalanceDaoImpl;
import dao.AccountInfoDao;
import dao.AccountInfoDaoImpl;
import model.AccountBalance;
import model.AccountInfo;

public class BankAccount {

	public static void main(String[] args) {

		String accountName;
		String address;
		String mobileNo;
		String type;
		double depositAmount = 0;
		double withdrawAmount = 0;
		double balance = 0;
		int newAccountNum = 0;

		String decision = "";
		do {
			System.out.println("Enter the corresponding number for your desired opertation: ");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit  ");
			System.out.println("3. Withdraw ");

			Scanner input = new Scanner(System.in);

			int operation = input.nextInt();

			AccountInfoDao accountInfoDao = new AccountInfoDaoImpl();
			AccountBalanceDao accountBalanceDao = new AccountBalanceDaoImpl();

			switch (operation) {
			case (1):
				System.out.println();
				System.out.println("Enter account name: ");
				accountName = input.next();

				System.out.println();
				System.out.println("Enter address: ");
				address = input.next();

				System.out.println();
				System.out.println("Enter mobile number: ");
				mobileNo = input.next();

				System.out.println();
				System.out.println("Enter account type: ");
				type = input.next();
				System.out.println();

				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountName(accountName);
				accountInfo.setAddress(address);
				accountInfo.setMobileNo(mobileNo);
				accountInfo.setType(type);

				newAccountNum = accountInfoDao.createAccountInfo(accountInfo);

				List<AccountInfo> accountInfoList = accountInfoDao.getAccountInfo(newAccountNum);

				accountInfoList.forEach(u -> {
					System.out.println();
					System.out.println("------------------------------------------");

					System.out.println("Account number: " + u.getAccountNum());
					System.out.println("Account name: " + u.getAccountName());
					System.out.println("Account address: " + u.getAddress());
					System.out.println("Account type: " + u.getType());

					System.out.println("------------------------------------------");
					System.out.println();
				});
				accountBalanceDao.createInitialBalance(newAccountNum);

				break;

			case (2):
				if (decision.equals("")) {

					System.out.println("Please enter your account number: ");
					newAccountNum = input.nextInt();

				}
				System.out.println();

				System.out.println("Enter the amount you want to deposit");
				depositAmount = input.nextDouble();
				accountBalanceDao.depositbalance(depositAmount, newAccountNum);

				AccountBalance accountBalanceInfo = accountBalanceDao.getAccountBalance(newAccountNum);
				System.out.println();
				System.out.println("------------------------------------------");

				System.out.println("Transaction Summary");
				System.out.println("Account Number: " + accountBalanceInfo.getAccountInfoId());
				System.out.println("Amount Deposited: " + accountBalanceInfo.getDepositAmount());
				System.out.println("Total Balance:" + accountBalanceInfo.getBalance());
				System.out.println("------------------------------------------");
				System.out.println();

				break;

			case (3):
				System.out.println("Enter the amount to withdraw: ");
				
				withdrawAmount = input.nextDouble();
				accountBalanceDao.withdrawBalance(withdrawAmount, newAccountNum);
				AccountBalance accountBalanceInfo1 = accountBalanceDao.getAccountBalance(newAccountNum);
				
				System.out.println();
				System.out.println("------------------------------------------");
				System.out.println("Transaction Summary");
				System.out.println("Account Number: " + accountBalanceInfo1.getAccountInfoId());
				System.out.println("Amount Deposited: " + accountBalanceInfo1.getDepositAmount());
				System.out.println("Total Balance:" + accountBalanceInfo1.getBalance());
				System.out.println("------------------------------------------");
				System.out.println();
			
				break;

			}

			System.out.println("Do you want to perform next operation?");
			decision = input.next();
		} while (decision.equalsIgnoreCase("yes"));
	}
}
