package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revature.models.BankAccount;
import com.revature.models.UserAccount;

public class UserAccountPostgresDAO extends AbstractPostgresDAO implements UserAccountDAO {
	
	@Override
	public UserAccount getUserById(int id) {
		List<UserAccount> uList = getAllUserAccounts();
		
		for(UserAccount u : uList) {
			if(u.getUserId() == id) return u;
		}
		
		return null;
	}
	
	@Override
	public UserAccount updateUserInfo(UserAccount u) {
		Connection conn = cf.getConnection();
		String sql = "update \"users\" set \"type\" = ?"
				+ " where \"user_id\" = ?" + "returning \"user_id\";";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql);
			new_user.setInt(1, u.getType());
			new_user.setInt(2, u.getUserId());
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
		return getUserById(u.getUserId());
	}
	
	
	@Override
	public HashMap<String, String> getLoginInfo() {
		Connection conn = cf.getConnection();
		HashMap<String, String> user_password_pairs = new HashMap<String,String>();
		
		try {
			String sql = "select * from users;";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				user_password_pairs.put(res.getString("username"),res.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user_password_pairs;
	}
	
	@Override
	public void saveNewLoginInfo(String username, String password) {
		Connection conn = cf.getConnection();
		String sql = "insert into \"users\" (\"username\",\"password\") values (?, ?)"
				+ " returning \"user_id\";";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql);
			new_user.setString(1, username);
			new_user.setString(2, password);
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
	}
	
	@Override
	public List<UserAccount> getAllUserAccounts() {
		Connection conn = cf.getConnection();
		ArrayList<UserAccount> allAccounts = new ArrayList<UserAccount>();
		
		try {
			String sql = "select * from users;";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				allAccounts.add(new UserAccount(
						res.getString("username"),
						res.getString("password"),
						res.getInt("type"),
						res.getInt("user_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allAccounts;
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


}
