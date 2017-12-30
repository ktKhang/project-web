package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Account;
import DBConnection.DBConnection;

public class AccountDAO {
	public static Boolean Insert_Account(Account account){
		Boolean flag=false;
		try {
			
            PreparedStatement preparedStatement = DBConnection.CreateConnection().prepareStatement(
            		"insert into account(accountname,role_id,password,name,birthday,sex,mail,phonenumber) value(?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, account.getAccountname());
            preparedStatement.setInt(2, 3);
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getName());
            preparedStatement.setString(5, account.getBirthday());
            preparedStatement.setString(6, account.getSex());
            preparedStatement.setString(7, account.getMail());
            preparedStatement.setString(8, account.getPhonenumber());
            preparedStatement.executeUpdate();
            flag=true;
 
        } catch (SQLException e) {
            e.printStackTrace();
            flag=false;
        }
		return flag;
	}
	public static Account LoadAccountbyAccountID(int account_id) throws SQLException{
		Connection conn = DBConnection.CreateConnection();	
		Account user= new Account();
		String str="SELECT * FROM account WHERE account_id=? ";
		
		PreparedStatement pstm = conn.prepareStatement(str);
        pstm.setInt(1, account_id);
        ResultSet rs = pstm.executeQuery();
		
		//Kiểm tra user và pass
		if (rs.next()) {
           	
			Integer account_id1=rs.getInt("account_id");
			String accountname=rs.getString("accountname");
			String avatar=rs.getString("avatar");
			Integer role_id= rs.getInt("role_id");
			String name= rs.getString("name");
			String birthday=rs.getString("birthday");
			String sex= rs.getString("sex");
			String mail=rs.getString("mail");
			String phonenumber=rs.getString("phonenumber");
            	
			user.setAccount_id(account_id1);
			user.setAccountname(accountname);
			user.setAvatar(avatar);
			user.setRole_id(role_id);
			user.setName(name);
			user.setBirthday(birthday);
			user.setSex(sex);
			user.setMail(mail);
			user.setPhonenumber(phonenumber);
			
			conn.close();
			return user;
        }
		return null;
	}

}
