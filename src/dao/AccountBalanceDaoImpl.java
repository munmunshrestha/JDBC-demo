package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AccountBalance;
import model.AccountInfo;
//import model.AccountBalance;
import util.DBUtil;

public class AccountBalanceDaoImpl implements AccountBalanceDao {

	public static final String INSERT_SQL = "Insert into account_balance_tbl(account_info_id) values(?)";
	public static final String UPDATE_SQL = "update account_balance_tbl set deposit_amount = ?, balance=? where account_info_id=?";
	public static final String UPDATE_SQL1 = "update account_balance_tbl set withdraw_amount=?, balance=? where account_info_id=?";
	public static final String SELECTONE_SQL = "select * from account_balance_tbl where account_info_id=?";

	@Override
	public void createInitialBalance(int accountNum ) {
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(INSERT_SQL);) {
//			ps.setDouble(1, accountBalance.getBalance());
			ps.setInt(1, accountNum);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void depositbalance(double depositAmount, int accountNum) {
		AccountBalance accountBalanceInfo = new AccountBalance();
		accountBalanceInfo=getAccountBalance(accountNum);
		
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(UPDATE_SQL);) {
			ps.setDouble(1, depositAmount);
			ps.setDouble(2, accountBalanceInfo.getBalance()+depositAmount);
			ps.setInt(3, accountNum);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void withdrawBalance(double WithdrawAmount, int accountNum) {
		AccountBalance accountBalanceInfo = new AccountBalance();
		accountBalanceInfo=getAccountBalance(accountNum);
		
		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(UPDATE_SQL1);) {
			ps.setDouble(1, WithdrawAmount);
			ps.setDouble(2, accountBalanceInfo.getBalance()-WithdrawAmount);
			ps.setInt(3, accountNum);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public AccountBalance getAccountBalance(int accountNum) {
//		List<AccountBalance> accountBalanceList = new ArrayList<>();
		AccountBalance accountBalanceInfo = new AccountBalance();

		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(SELECTONE_SQL);) {
			ps.setInt(1, accountNum);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				accountBalanceInfo.setAccountInfoId(rs.getInt("account_info_id"));
				accountBalanceInfo.setBalance(rs.getDouble("balance"));
				accountBalanceInfo.setDepositAmount(rs.getDouble("deposit_amount"));
				accountBalanceInfo.setWithdrawAmount(rs.getDouble("withdraw_amount"));

//				accountBalanceList.add(accountBalance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountBalanceInfo;
	}

	

	

}
