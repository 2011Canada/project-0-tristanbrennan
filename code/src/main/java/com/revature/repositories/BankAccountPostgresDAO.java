package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.MoneyTransfer;
import com.revature.models.UserAccount;

public class BankAccountPostgresDAO extends AbstractPostgresDAO implements BankAccountDAO {

	@Override
	public BankAccount getAccountByIdNumber(int id_number) {
		List<BankAccount> uList = getAllBankAccounts();
		
		for(BankAccount u : uList) {
			if(u.getAccountId() == id_number) return u;
		}
		
		return null;
	}

	@Override
	public void createNewBankAccount(BankAccount b) {
			Connection conn = cf.getConnection();
			String sql = "insert into \"accounts\" (\"balance\",\"user_id\",\"verified\") values (?, ?, ?)"
					+ " returning \"user_id\";";
			
			try {
				PreparedStatement new_acct = conn.prepareStatement(sql);
				new_acct.setDouble(1, b.getBalance());
				new_acct.setInt(2, b.getOwnerId());
				new_acct.setBoolean(3, b.isVerified());
				
				new_acct.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//must use prepared statement instead of a regular statement when dealing with user input of any kind
			//this is to prevent sql injection
		}

	@Override
	public void updateBankAccountInfo(BankAccount b) {
		Connection conn = cf.getConnection();
		String sql = "update \"accounts\" set \"balance\" = ?"
				+ " where \"account_id\" = ?" + "returning \"account_id\";";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql);
			new_user.setDouble(1, b.getBalance());
			new_user.setInt(2, b.getAccountId());
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql2 = "update \"accounts\" set \"verified\" = ?"
				+ " where \"account_id\" = ?" + "returning \"account_id\";";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql2);
			new_user.setBoolean(1, b.isVerified());
			new_user.setInt(2, b.getAccountId());
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
	}
	
	@Override
	public List<BankAccount> getAllBankAccounts() {
		Connection conn = cf.getConnection();
		ArrayList<BankAccount> allAccounts = new ArrayList<BankAccount>();
		
		try {
			String sql = "select * from accounts;";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				allAccounts.add(new BankAccount(
						res.getInt("user_id"), 
						res.getDouble("balance"),
						res.getInt("account_id"),
						res.getBoolean("verified")));
				//user_password_pairs.put(res.getString("username"),res.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allAccounts;
	}

	@Override
	public void createMoneyTransfer(MoneyTransfer mt) {
		Connection conn = cf.getConnection();
		String sql = "insert into \"transfers\" (\"origin_id\",\"target_id\",\"sum\") values (?,?,?)"
				+ " returning \"transaction_id\";";
		
		try {
			PreparedStatement new_acct = conn.prepareStatement(sql);
			new_acct.setInt(1, mt.getOrigin().getAccountId());
			new_acct.setInt(2, mt.getTarget().getAccountId());
			new_acct.setDouble(3, mt.getSum());
			
			new_acct.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
	}

	@Override
	public List<MoneyTransfer> getAllMoneyTransfers() {
		Connection conn = cf.getConnection();
		ArrayList<MoneyTransfer> allAccounts = new ArrayList<MoneyTransfer>();
		
		try {
			String sql = "select * from transfers;";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				int id;
				int n1, n2;
				double s1;
				
				id = res.getInt("transaction_id");
				n1 = res.getInt("origin_id");
				n2 = res.getInt("target_id");
				s1 = res.getDouble("sum");
				
				allAccounts.add(new MoneyTransfer(
						id,
						getAccountByIdNumber(n1),
						getAccountByIdNumber(n2),
						s1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allAccounts;
	}

	@Override
	public void resolveMoneyTransfer(int id) {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM \"transfers\" WHERE \"transaction_id\" = ? returning \"transaction_id\";";
		
		try {
			PreparedStatement new_acct = conn.prepareStatement(sql);
			new_acct.setInt(1, id);
			
			new_acct.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
		
		
	}

}
