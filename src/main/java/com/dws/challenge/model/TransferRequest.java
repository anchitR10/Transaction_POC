package com.dws.challenge.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TransferRequest {
    private String accountFrom;
    private String accountTo;
    private BigDecimal amount;
}