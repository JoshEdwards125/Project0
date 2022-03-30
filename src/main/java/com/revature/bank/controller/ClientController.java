package com.revature.bank.controller;
import io.javalin.http.Handler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.revature.bank.model.Client;
import com.revature.jdbc.ConnectionUtils;
import java.sql.*;

public class ClientController{
	public static Handler newClient = ctx -> {
		
		Client cl = ctx.bodyAsClass(Client.class);
		Connection conn = ConnectionUtils.createConnection();
		PreparedStatement pstmt;
		ResultSet rs;
		String insert = "insert into client values(?, ?)";
		pstmt = conn.prepareStatement(insert);
		pstmt.setInt(1, cl.getId());
		pstmt.setString(2, cl.getName());
		
		pstmt.execute();
		ctx.status(201);
		
		
		
	};
	public static Handler getAllClients = ctx->{ 
	    //get connection from the connectionUtils class
		Connection conn = ConnectionUtils.createConnection();
	    PreparedStatement pstmt;
	    ResultSet rs;
	    
	    //create a prepared Statement and execute for * student and fetch in result set
	    String selectAllClients = "select * from client";
	    try{
	    	 pstmt= conn.prepareStatement(selectAllClients);
		        rs = pstmt.executeQuery();

	        //create an Arraylist
	        ArrayList<Client> cliList= new ArrayList<Client>();
	        Client c;

	        while(rs.next()){
	            int id = rs.getInt("client_id");
	            String name = rs.getString("client_name");   
	            c = new Client(id,name);
	            cliList.add(c);
	        }
	        rs.close();
	        pstmt.close();
	        ctx.json(cliList);
	       

	    }catch (SQLException e){
	        e.printStackTrace();
	        ctx.status(200);
	    }
    };
    public static Handler getClientId = ctx ->  {
    	int client_id = Integer.parseInt(ctx.pathParam("client_id"));
    	Connection conn = ConnectionUtils.createConnection();
    	String selectClients = "select * from client where client_id =?";
    	try {
    	PreparedStatement pstmt = conn.prepareStatement(selectClients);
    	pstmt.setInt(1, client_id);
    	ResultSet rs = pstmt.executeQuery();
    	ArrayList<Client> cList = new ArrayList<Client>();
    	Client c;
    	while (rs.next()) {
    	    client_id =rs.getInt("client_id");
    		String name = rs.getString("client_name");
    		c = new Client(client_id, name);
    		cList.add(c);
    	}
    	ctx.json(cList);
    	rs.close();
    	pstmt.close();
    	}catch (SQLException f) {
    		f.printStackTrace();
    		ctx.status(404);
    	}
    	
    
    };
    public static Handler updateClient = ctx ->{
    	Client cl = ctx.bodyAsClass(Client.class);
    	int client_id = Integer.parseInt(ctx.pathParam("client_id"));
    	PreparedStatement pstmt;
    	Connection conn = ConnectionUtils.createConnection();
    	try {
    	pstmt = conn.prepareStatement ("update client set client_name=? where client_id=?");
    	pstmt.setString(1, cl.getName());
    	
    	pstmt.setInt(2, client_id);
    	pstmt.execute();
    	pstmt.close();
   
    		}   catch (SQLException j)
    	{
    		j.printStackTrace();
    		ctx.status(404);
    	}
    	
    };
    public static Handler deleteClient = ctx ->{
    	int id = Integer.parseInt(ctx.pathParam("client_id"));
    	Connection conn = ConnectionUtils.createConnection();
    	PreparedStatement pstmt = conn.prepareStatement("DELETE from client where client_id=?");
    	try {
        	pstmt.setInt(1, id);
        	pstmt.execute();
        	pstmt.close();
        	
    	}catch (SQLException h)
    	{
    		h.printStackTrace();
    		ctx.status(404);
    	}
    };
    	
}