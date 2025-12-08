package org.example.sq.part1.SmartHomeController.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.sq.part1.SmartHomeController.dto.LoginDTO;
import org.example.sq.part1.SmartHomeController.services.RequestContext;
import org.example.sq.part1.SmartHomeController.services.SessionUser;
import org.example.sq.part1.SmartHomeController.services.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService service;
    private final RequestContext requestContext;
    private final SessionUser sessionUser;

    public LoginController(LoginService service, RequestContext requestContext, SessionUser sessionUser) {
        this.service=service;
        this.requestContext=requestContext;
        this.sessionUser=sessionUser;
    }

    @GetMapping("/home")
    public String getHome(Model page, HttpServletRequest request){
        requestContext.setClientIp(request.getRemoteAddr());
        requestContext.setUserAgent(request.getHeader("User-Agent"));

        page.addAttribute("requestId", requestContext.getRequestId());
        page.addAttribute("startedAt", requestContext.getStartedAt());
        page.addAttribute("clientIp", requestContext.getClientIp());
        page.addAttribute("userAgent", requestContext.getUserAgent());
        page.addAttribute("authenticated", sessionUser.isAuthenticated());
        page.addAttribute("username", sessionUser.getUsername());
        return "SmartProject/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model page,
            HttpServletRequest req
    ) {
        requestContext.setClientIp(req.getRemoteAddr());
        requestContext.setUserAgent(req.getHeader("User-Agent"));

        LoginDTO.Response dto = service.login(new LoginDTO.Request(username,password));

        page.addAttribute("message", dto.message());
        page.addAttribute("requestId", dto.requestId());
        page.addAttribute("authenticated", dto.authenticated());

        return dto.page();
    }

    @PostMapping("/logout")
    public String logout() {
        LoginDTO.Response response = service.logout();
        return response.page();
    }
}
