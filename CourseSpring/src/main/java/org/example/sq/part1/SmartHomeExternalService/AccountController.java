package org.example.sq.part1.SmartHomeExternalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody AccountDTO.Request request
    ) {
        AccountDTO.Response accountResponse = accountService.registerUser(request);
        if (accountResponse.isRegistered())
            return ResponseEntity.ok()
                    .body("Account" + request.username() + " was successful registered.!");
        return ResponseEntity.badRequest()
                .body("Can't register account!. Username: " + request.username());
    }
}
