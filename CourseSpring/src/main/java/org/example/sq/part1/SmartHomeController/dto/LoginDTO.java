package org.example.sq.part1.SmartHomeController.dto;

public class LoginDTO {
     public record Response(String message, String page, String requestId, boolean authenticated) {
     }
     public record Request(String username, String password){}
}
