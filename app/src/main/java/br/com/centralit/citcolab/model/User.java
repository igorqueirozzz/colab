package br.com.centralit.citcolab.model;

public class User {
    private String employerID;
    private String name;
    private String email;
    private String password;
    private String office;
    private String localOffice;
    private String photoProfileURL;

    public User(String employerID, String name, String email, String password, String office, String localOffice, String photoProfileURI) {
        this.employerID = employerID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.office = office;
        this.localOffice = localOffice;
        this.photoProfileURL = photoProfileURI;
    }

    public String getEmployerID() {
        return employerID;
    }

    public void setEmployerID(String employerID) {
        this.employerID = employerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    public String getPassword() {
        return password;
    }*/

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLocalOffice() {
        return localOffice;
    }

    public void setLocalOffice(String localOffice) {
        this.localOffice = localOffice;
    }

    public String getPhotoProfileURL() {
        return photoProfileURL;
    }

    public void setPhotoProfileURI(String photoProfileURL) {
        this.photoProfileURL = photoProfileURL;
    }
}
