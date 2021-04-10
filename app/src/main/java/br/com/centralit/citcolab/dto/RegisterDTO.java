package br.com.centralit.citcolab.dto;


import java.io.Serializable;


import br.com.centralit.citcolab.model.User;


public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = -2340861764386969945L;

    private Long user_id;
    private String user_location;
    private String register_date;
    private String register_time;
    private String reference;
    private User userEntity;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }
}
