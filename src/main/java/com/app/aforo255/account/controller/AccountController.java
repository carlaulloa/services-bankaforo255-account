package com.app.aforo255.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.aforo255.account.model.entity.Account;
import com.app.aforo255.account.service.IAccountService;

@RestController
public class AccountController {

	private @Autowired IAccountService accountService;
	
	@GetMapping("/v1/accounts")
	public List<Account> listAccounts(){
		return this.accountService.findAll();
	}
	
	@GetMapping("/v1/accounts/{id}")
	public Account detalle (@PathVariable Integer id) {
		Account account = this.accountService.findById(id);
		return account ;
	}
}
