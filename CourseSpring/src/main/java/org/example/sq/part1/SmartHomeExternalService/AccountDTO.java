package org.example.sq.part1.SmartHomeExternalService;

public class AccountDTO {
    public record Request(String username, String password){}
    public record Response(String username, boolean isRegistered){}
}
