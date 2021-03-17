package br.com.centralit.citcolab.model;

public class User {
    private Integer id;
    private String employer_id;
    private String user_name;
    private String user_cpf;
    private String user_email;
    private String user_password;
    private String user_occupation;
    private String office_local;
    private String photoProfileURL;

    public User() {
    }

    public User(Integer id, String employer_id, String user_name, String user_cpf, String user_email, String user_password, String user_occupation, String office_local, String photoProfileURL) {
        this.id = id;
        this.employer_id = employer_id;
        this.user_name = user_name;
        this.user_cpf = user_cpf;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_occupation = user_occupation;
        this.office_local = office_local;
        this.photoProfileURL = photoProfileURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_occupation() {
        return user_occupation;
    }

    public void setUser_occupation(String user_occupation) {
        this.user_occupation = user_occupation;
    }

    public String getOffice_local() {
        return office_local;
    }

    public void setOffice_local(String office_local) {
        this.office_local = office_local;
    }

    public String getPhotoProfileURL() {
        return photoProfileURL;
    }

    public void setPhotoProfileURL(String photoProfileURL) {
        this.photoProfileURL = photoProfileURL;
    }
}
