package com.revature.bank.model;

import com.revature.bank.controller.AccountController;
import com.revature.bank.controller.ClientController;

import io.javalin.Javalin;

public class MainBank {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		
		app.post("/clients", ClientController.newClient);
		app.get("/clients", ClientController.getAllClients);
		app.get("/clients/{client_id}", ClientController.getClientId);
		app.put("/client/{client_id}", ClientController.updateClient);
		app.delete("/clients/{client_id}", ClientController.deleteClient);
		app.post("/account", AccountController.newAccount);
		app.get("/account", AccountController.selectAllAccounts);
		
		
		
	}

}
