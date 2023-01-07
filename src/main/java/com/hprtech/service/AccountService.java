package com.hprtech.service;

import com.hprtech.dto.Account;
import com.hprtech.resource.Resource;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountService {
    public static final Logger LOG = Logger.getLogger(AccountService.class);

    public boolean isAccountAlreadyExist(Account account1) {
        LOG.info("Entering in AccountService::isAccountAlreadyExist()");
        LOG.debug("AccountService::isAccountAlreadyExist() Account1 " + account1);

        Account account = getAccountByAccNumber(account1.getAccountNumber());
        LOG.debug("AccountService::isAccountAlreadyExist() account " + account);

        if (account != null) {
            LOG.info("Returning from AccountService::isAccountAlreadyExist()");
            return true;
        } else {
            LOG.info("Returning from AccountService::isAccountAlreadyExist()");
            return openNewAccount(account1);
        }
    }

    private boolean openNewAccount(Account accountNumber) {
        LOG.info("Entering in AccountService::openNewAccount()");
        LOG.debug("AccountService::openNewAccount() Account " + accountNumber);
        LOG.info("Returning from AccountService::openNewAccount()");
        return true;
    }

    private Account getAccountByAccNumber(long accountNumber) {
        LOG.info("Entering in AccountService::getAccountByAccNumber()");
        LOG.debug("AccountService::getAccountByAccNumber() accountNumber " + accountNumber);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setName("Rakesh");
        LOG.debug("AccountService::getAccountByAccNumber() account " + account);

        LOG.info("Returning from AccountService::isAccountAlreadyExist()");
        return account;
    }
}
