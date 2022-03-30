package com.revature.bank.controller;
import io.javalin.http.Handler;

import java.util.ArrayList;
import com.revature.bank.model.Account;

import com.revature.jdbc.ConnectionUtils;
import java.sql.*;

public class AccountController {

	public static Handler newAccount = ctx -> {
		
		Account ac = ctx.bodyAsClass(Account.class);
		Connection conn = ConnectionUtils.createConnection();
		PreparedStatement pstmt;
		ResultSet rs;
		String insert = "insert into account values(?, ?, ?, ?)";
		pstmt = conn.prepareStatement(insert);
		pstmt.setInt(1, ac.getA_id());
		pstmt.setString(2, ac.getNumber());
		pstmt.setInt(3, ac.getBalance());
		pstmt.setInt(4, ac.getC_id());
		pstmt.execute();
		ctx.status(201);
		
	};
	public static Handler selectAllAccounts = ctx -> {
		
	Connection conn = ConnectionUtils.createConnection();
    PreparedStatement pstmt;
    ResultSet rs;
    
    //create a prepared Statement and execute for * student and fetch in result set
    String allAccounts = "select * from account";
    try{
    	 pstmt= conn.prepareStatement(allAccounts);
	        rs = pstmt.executeQuery();

        //create an Arraylist
        ArrayList<Account> AList= new ArrayList<Account>();
        Account a;

        while(rs.next()){
            int a_id = rs.getInt("account_id");
            String number = rs.getString("account_number");
            int balance = rs.getInt("account_balance"); 
            int c_id = rs.getInt("client_id"); 
            a = new Account(a_id, c_id, number, balance);
            AList.add(a);
        }
        rs.close();
        pstmt.close();
        ctx.json(AList);
       

    }catch (SQLException p){
        p.printStackTrace();
        ctx.status(404);
    }
	
	};
}
