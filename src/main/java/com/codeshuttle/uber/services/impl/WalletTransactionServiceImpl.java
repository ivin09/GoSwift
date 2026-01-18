package com.codeshuttle.uber.services.impl;

import com.codeshuttle.uber.dto.WalletTransactionDto;
import com.codeshuttle.uber.entities.WalletTransaction;
import com.codeshuttle.uber.repositories.WalletTransactionRepository;
import com.codeshuttle.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
