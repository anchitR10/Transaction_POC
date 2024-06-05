package com.dws.challenge.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.challenge.domain.Account;
import com.dws.challenge.model.TransferRequest;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.NotificationService;
import com.dws.challenge.service.TransferService;

@RestController
@RequestMapping("/transferAmount")
public class TransferController {
	
	@Autowired
    private AccountsService accountService;
	
	 @Autowired
	 private NotificationService notificationService;
	 
	 @Autowired
	 private TransferService transferService;
	 
    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferRequest transferRequest) {
    	try {
            transferService.transfer_Money(transferRequest);
            return ResponseEntity.ok("Money transferred successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        } 
    
   }
    	

}
