package org.example.sq.part1.SmartHomeController.callOtherService;

import feign.Headers;
import org.example.sq.part1.SmartHomeExternalService.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="register" ,
        url = "http://localhost:8081")
public interface RegisterProxy {

    @PostMapping(value = "/register", headers = "Content-Type: application/json")
    ResponseEntity<String> register(
            @RequestBody AccountDTO.Request request
    );
}
