package org.example.sq.part1.SmartHomeController.services.login;

import org.example.sq.part1.SmartHomeController.callOtherService.RegisterProxy;
import org.example.sq.part1.SmartHomeController.dto.LoginDTO;
import org.example.sq.part1.SmartHomeExternalService.AccountDTO;
import org.example.sq.part1.SmartHomeController.model.LoginModel;
import org.example.sq.part1.SmartHomeController.repository.loginRepository.LoginRepository;
import org.example.sq.part1.SmartHomeController.services.RequestContext;
import org.example.sq.part1.SmartHomeController.services.SessionUser;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final SessionUser sessionUser;
    private final RequestContext requestContext;
    private final RegisterProxy registerProxy;

    public LoginService(LoginRepository loginRepository, SessionUser sessionUser, RequestContext requestContext, RegisterProxy registerProxy) {
        this.loginRepository = loginRepository;
        this.sessionUser=sessionUser;
        this.requestContext=requestContext;
        this.registerProxy = registerProxy;
    }

    public LoginDTO.Response login(LoginDTO.Request requestDTO) {
        try {
            List<LoginModel> loginObjects = loginRepository.getLoginObjects(requestDTO);
            String result;
            if (loginObjects.size()==1) {
                LoginModel user = loginObjects.getFirst();
                sessionUser.setUserId(user.getId());
                sessionUser.setUsername(user.getUsername());
                sessionUser.setAuthenticated(true);

                result = "Username is :" + requestDTO.username()
                        + "\n and password: " + requestDTO.password();
                return new LoginDTO.Response(result,"SmartProject/home",requestContext.getRequestId(), sessionUser.isAuthenticated());
            } else {
                result = "Can't login user: " + requestDTO.username() + " with password :" + requestDTO.password();
                return new LoginDTO.Response(result,"SmartProject/fail", requestContext.getRequestId(), sessionUser.isAuthenticated());
            }
        } catch (DataAccessException e){
            System.out.println("Can't access data." + "\n" + e);
            return new LoginDTO.Response("Can't login user: " + requestDTO.username() + " with password :" + requestDTO.password() + "\n ERROR:" + e,"SmartProject/fail",requestContext.getRequestId(), sessionUser.isAuthenticated());
        }
    }

    public LoginDTO.Response logout() {
        sessionUser.clear();
        return new LoginDTO.Response("", "redirect:/home", "", false);
    }

    public LoginDTO.Response register(AccountDTO.Request request) {
        ResponseEntity<String>  response = registerProxy.register(request);
        if (response==null)
            return new LoginDTO.Response("Something went wrong with calling the endpoint","SmartProject/fail", requestContext.getRequestId(),false);
        if (response.getStatusCode().is2xxSuccessful()) {
            return new LoginDTO.Response(response.getBody(),"SmartProject/home", requestContext.getRequestId(), true);
        }
        return new LoginDTO.Response(response.getBody(),"SmartProject/fail", requestContext.getRequestId(), false);
    }

}
