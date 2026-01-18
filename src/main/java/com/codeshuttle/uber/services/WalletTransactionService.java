package com.codeshuttle.uber.services;

import com.codeshuttle.uber.dto.WalletTransactionDto;
import com.codeshuttle.uber.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransact);
}
