package org.example.ex1.SmartHomeTest;

import org.example.sq.part1.SmartHomeExternalService.AccountDTO;
import org.example.sq.part1.SmartHomeExternalService.AccountRepository;
import org.example.sq.part1.SmartHomeExternalService.AccountService;
import org.example.sq.part1.SmartHomeExternalService.MainApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = MainApp.class)
public class AccountServiceIntegrationTest {

    @Autowired
    private  AccountRepository accountRepository;

    @Autowired
    private  AccountService service;

    @Test
    @DisplayName("Test the integration registration in a successful scenario.")
    public void registerUserTest(){
        AccountDTO.Request request = new AccountDTO.Request("Test","pass");
        AccountDTO.Response response = service.registerUser(request);
        assert(response.isRegistered() && response.username().equals(request.username()));
    }

    @Test
    @DisplayName("The username already exists. Throw an exception.")
    public void registerUserTest2(){
        AccountDTO.Request request = new AccountDTO.Request("Test","pass");
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                ()->service.registerUser(request)
        );

        assert("Username already exists.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("The password is empty. Throw an exception.")
    public void registerUserTest3(){
        AccountDTO.Request request = new AccountDTO.Request("Test123","");
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                ()->service.registerUser(request)
        );

        assert("Password must not be blank.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("The username is empty. Throw an exception.")
    public void registerUserTest4(){
        AccountDTO.Request request = new AccountDTO.Request("","123");
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                ()->service.registerUser(request)
        );

        assert("Username must not be blank.".equals(ex.getMessage()));
    }

}

