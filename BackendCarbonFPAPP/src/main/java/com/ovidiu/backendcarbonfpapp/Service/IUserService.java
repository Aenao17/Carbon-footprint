package com.ovidiu.backendcarbonfpapp.Service;
import com.ovidiu.backendcarbonfpapp.DTO.LoginRequest;
import com.ovidiu.backendcarbonfpapp.DTO.RegisterRequest;

public interface IUserService {


    void register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);
}
