package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AccountInfo;
import util.DBUtil;

public class AccountInfoDaoImpl implements AccountInfoDao {

	public static final String INSERT_SQL = "Insert into account_info_tbl(account_name, address, mobile_no, type) values(?,?,?,?)";
	public static final String SELECTALL_SQL = "select * from account_info_tbl";
	public static final String SELECTONE_SQL = "select * from account_info_tbl where account_num=?";

	@Override
	public int createAccountInfo(AccountInfo accountInfo) {
		AccountBalanceDao accountBalanceDao = new AccountBalanceDaoImpl();
		int newAccountNum = 0;

		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(INSERT_SQL,
				Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, accountInfo.getAccountName());
			ps.setString(2, accountInfo.getAddress());
			ps.setString(3, accountInfo.getMobileNo());
			ps.setString(4, accountInfo.getType());
			int rowAffected = ps.executeUpdate();
			ResultSet rs = null;

			if (rowAffected == 1) {
				// get account num created
				rs = ps.getGeneratedKeys();
				if (rs.next())
					newAccountNum = rs.getInt(1);

//                List<AccountInfo> accountInfoList = getAccountInfo(newAccountNum);
//				accountInfoList.forEach(u -> {
//					System.out.println("Account number: " + u.getAccountNum());
//					System.out.println("Account name: " + u.getAccountName());
//					System.out.println("Account address: " + u.getAddress());
//					System.out.println("Account type: " + u.getType());
//
//					accountInfo.setAccountNum(u.getAccountNum());
//
//				});
//
//				accountBalanceDao.createInitialBalance(accountInfo.getAccountNum());

			}


		} catch (SQLException e) {
			e.printStackTrace();

		}
		return newAccountNum;

	}

	@Override
	public List<AccountInfo> getAllAccountInfo() {

		List<AccountInfo> accountInfoList = new ArrayList<>();

		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(SELECTALL_SQL);) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountNum(rs.getInt("account_num"));
				accountInfo.setAccountName(rs.getString("account_name"));
				accountInfo.setAddress(rs.getString("address"));
				accountInfo.setType(rs.getString("type"));

				accountInfoList.add(accountInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountInfoList;
	}

	@Override
	public List<AccountInfo> getAccountInfo(int accountNum) {

		List<AccountInfo> accountInfoList = new ArrayList<>();

		try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(SELECTONE_SQL);) {
			ps.setInt(1, accountNum);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountNum(rs.getInt("account_num"));
				accountInfo.setAccountName(rs.getString("account_name"));
				accountInfo.setAddress(rs.getString("address"));
				accountInfo.setType(rs.getString("type"));

				accountInfoList.add(accountInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountInfoList;
	}

}
