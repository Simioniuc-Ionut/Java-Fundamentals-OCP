package org.example.ex1.SmartHomeTest;

import org.example.sq.part1.SmartHomeExternalService.AccountDTO;
import org.example.sq.part1.SmartHomeExternalService.AccountEntity;
import org.example.sq.part1.SmartHomeExternalService.AccountRepository;
import org.example.sq.part1.SmartHomeExternalService.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Test the registration successful")
    public void registerUserSuccessful(){
        AccountDTO.Request request = new AccountDTO.Request("Test","pass");

//        given(accountRepository.existsByName(request.username()))
//                .willReturn(false);
        when(accountRepository.existsByName(request.username()))
                .thenReturn(false);

        given(accountRepository.save(any()))
                .willReturn(new AccountEntity(1,"Test","pass"));

        AccountDTO.Response response = accountService.registerUser(request);
        assert(response.isRegistered() && response.username().equals(request.username()));

    }


    @Test
    @DisplayName("Username is blank.")
    public void registerUser_username_is_blank_throws_IllegalArgumentException() {
        AccountDTO.Request request = new AccountDTO.Request("", "123");


        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.registerUser(request)
        );

        assert("Username must not be blank.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("Password is blank.")
    public void registerUser_password_is_blank_throws_IllegalArgumentException() {
        AccountDTO.Request request = new AccountDTO.Request("Test", "");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.registerUser(request)
        );

        assert("Password must not be blank.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("Username is not unique.")
    public void registerUser_username_is_not_unique_throws_RuntimeException() {
        AccountDTO.Request request = new AccountDTO.Request("Test", "123");

        when(accountRepository.existsByName(request.username()))
                .thenReturn(true);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> accountService.registerUser(request)
        );

        assert("Username already exists.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("Save account returns an unexpected value")
    public void registerUser_save_account_throws_RuntimeException(){
        AccountDTO.Request request = new AccountDTO.Request("Test","pass");

        when(accountRepository.existsByName(request.username()))
                .thenReturn(false);

        given(accountRepository.save(any()))
                .willReturn(null);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> accountService.registerUser(request)
        );
        assert("Something unexpected happens when saving account.".equals(ex.getMessage()));
    }

    @Test
    @DisplayName("When saving an error occurred.")
    public void registerUser_save_throws_Exception(){
        AccountDTO.Request request = new AccountDTO.Request("Test","pass");
        when(accountRepository.existsByName(request.username()))
                .thenReturn(false);

        given(accountRepository.save(any()))
                .willThrow(new RuntimeException());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> accountService.registerUser(request)
        );
        assert ("Failed to save account".equals(ex.getMessage()));
    }

}
