package com.dws.challenge.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dws.challenge.domain.Account;
import com.dws.challenge.web.AccountsController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotificationService implements NotificationService {

	private static final Logger log = LogManager.getLogger(EmailNotificationService.class);
  @Override
  public void notifyAboutTransfer(Account account, String transferDescription) {
    //THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
    log
      .info("Sending notification to owner of {}: {}", account.getAccountId(), transferDescription);
  }

}
