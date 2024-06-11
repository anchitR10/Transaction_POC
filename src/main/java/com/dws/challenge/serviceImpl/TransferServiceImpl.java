package com.dws.challenge.serviceImpl;

import java.math.BigDecimal;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.model.TransferRequest;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.NotificationService;
import com.dws.challenge.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {


    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private AccountsService accountService;

    

	@Override
	public void transfer_Money(TransferRequest transferRequest) {
		  // Retrieve account details
        Account accountFrom = accountService.getAccount(transferRequest.getAccountFrom());
        Account accountTo = accountService.getAccount(transferRequest.getAccountTo());

       // checking lock acquisition order
        Account firstAcnt = accountFrom.getAccountId().compareTo(accountTo.getAccountId()) < 0 ? accountFrom : accountTo;
        Account scndAcnt = firstAcnt == accountFrom ? accountTo : accountFrom;

    	synchronized (firstAcnt) {
            synchronized (scndAcnt) {
                
    	
    	// Validate request body
        if (transferRequest.getAccountFrom() == null || transferRequest.getAccountTo() == null || (transferRequest.getAmount().compareTo(BigDecimal.ZERO)) <= 0) {
            return ;
        }

      
        // Validate accounts
        if (accountFrom == null || accountTo == null) {
            return ;
        }

        // Check balance of accountFrom
        if (accountFrom.getBalance().compareTo(transferRequest.getAmount())<0) {
            return ;
        }
        
        if((accountFrom.getBalance().subtract(transferRequest.getAmount())).compareTo(BigDecimal.ZERO)<0) {
        	return ;
        }
        
        

        // Perform transfer
        accountFrom.setBalance(accountFrom.getBalance().subtract(transferRequest.getAmount()));
        accountTo.setBalance(accountTo.getBalance().add(transferRequest.getAmount()));

        // Send notifications
        notificationService.notifyAboutTransfer(accountFrom, "Amount of " + transferRequest.getAmount() + " transferred to account " + accountTo);
        notificationService.notifyAboutTransfer(accountTo, "Amount of " + transferRequest.getAmount() + " received from account " + accountFrom);
   }
    }
    }
		
}

	
