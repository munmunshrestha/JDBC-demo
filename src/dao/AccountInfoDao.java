package dao;

import java.util.List;

import model.AccountInfo;

public interface AccountInfoDao {

	int createAccountInfo(AccountInfo accountInfo);
	List<AccountInfo> getAllAccountInfo();
	List<AccountInfo> getAccountInfo(int accountNum);

}
