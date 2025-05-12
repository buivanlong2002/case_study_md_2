package controller;

import model.Account;

import service.AccountService;

public class AuthController {
    private final AccountService accountService = new AccountService();

    public Account login(String username, String password) {
        return accountService.login(username, password);
    }

    public void register(String username, String password) {
        accountService.register(username, password);
    }

    public AccountService getAccountService() {
        return accountService;
    }
}

