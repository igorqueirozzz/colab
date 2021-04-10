package br.com.centralit.citcolab.model;


import java.util.Date;

public class PointRegister {

    private Long id;
    private String register_date;
    private String register_local;
    private String register_time;
    private String reference;
    private Long user_id;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getRegister_local() {
        return register_local;
    }

    public void setRegister_local(String register_local) {
        this.register_local = register_local;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}