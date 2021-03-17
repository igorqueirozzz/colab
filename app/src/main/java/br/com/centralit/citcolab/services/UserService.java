package br.com.centralit.citcolab.services;

import br.com.centralit.citcolab.controller.UserController;
import br.com.centralit.citcolab.repository.UserRepository;

public class UserService {

    private String apiUrl = "";

    public static UserController getUserRepository(String apiUrl){
        return UserRepository.getUser(apiUrl).create(UserController.class);
    }
}
