package br.com.app.colab.dto;


public class UserCredentials {
    private String email_login;
    private String password;

    public UserCredentials(String email_login, String password) {
        this.email_login = email_login;
        this.password = password;
    }
}
