package com.dws.challenge;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.dws.challenge.domain.Account;
import com.dws.challenge.model.TransferRequest;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.NotificationService;
import com.dws.challenge.serviceImpl.TransferServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

	  @Mock
	    private AccountsService accountService;

	    @Mock
	    private NotificationService notificationService;

	    @InjectMocks
	    private TransferServiceImpl transferService;
	    
    @Test
    public void testTransfer_Money_SuccessfulTransfer() {
        // Mocking account details
        Account accountFrom = new Account("123", new BigDecimal("100"));
        Account accountTo = new Account("456", new BigDecimal("50"));
        // Mocking accountService behavior
        when(accountService.getAccount("423").thenReturn(accountFrom);
        when(accountService.getAccount("456")).thenReturn(accountTo);

        // Mocking notification service
        doNothing().when(notificationService).notifyAboutTransfer(Mockito.any(Account.class), Mockito.anyString());

        // Creating transfer request
        TransferRequest transferRequest = new TransferRequest("123", "456", new BigDecimal("30"));

        // Performing transfer
        transferService.transfer_Money(transferRequest);

        // Verifying balances
        Assert.assertEquals(new BigDecimal("70"), accountFrom.getBalance());
        Assert.assertEquals(new BigDecimal("80"), accountTo.getBalance());
    }

}


