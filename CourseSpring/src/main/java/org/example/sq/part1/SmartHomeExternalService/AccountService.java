package org.example.sq.part1.SmartHomeExternalService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDTO.Response registerUser(AccountDTO.Request request) {

        if (request == null || request.username() == null || request.username().isBlank()) {
            throw new IllegalArgumentException("Username must not be blank.");
        }
        if (request.password() == null || request.password().isBlank()) {
            throw new IllegalArgumentException("Password must not be blank.");
        }

        if (accountRepository.existsByName(request.username())) {
            throw new RuntimeException("Username already exists.");
        }

        AccountEntity entity = new AccountEntity();
//        int id = (int) (Math.random() * 1000);
//        entity.setId(id);
        entity.setName(request.username());
        entity.setPassword(request.password());
        AccountEntity account;
        try {
            account =  accountRepository.save(entity);
        } catch (Exception ex) {
            Throwable root = org.springframework.core.NestedExceptionUtils.getMostSpecificCause(ex);
            System.out.println("Failed to save account. Root cause: {}" +  root.getMessage() +  ex);
            throw ex;
        }

        boolean isRegistered = !account.getName().isBlank() && !account.getPassword().isBlank();
        return new AccountDTO.Response(account.getName(),isRegistered);
    }
}
