package br.com.centralit.citcolab.model;



import br.com.centralit.citcolab.enumeration.AccessLevel;
import br.com.centralit.citcolab.enumeration.GenderEnum;
import br.com.centralit.citcolab.enumeration.SectorEnum;


public class User {
    private Long id;
    private Long employerId;
    private String cpf;
    private String user_name;
    private GenderEnum gender;
    private String email;
    private String user_password;
    private String occupation;
    private String local_office;
    private SectorEnum sector;
    private AccessLevel access_level;
    private String photo_profile;


    public User(){}

    public User(Long id, Long employerId, String cpf, String user_name, GenderEnum gender, String email, String user_password, String occupation, String local_office, SectorEnum sector, AccessLevel access_level, String photo_profile) {
        this.id = id;
        this.employerId = employerId;
        this.cpf = cpf;
        this.user_name = user_name;
        this.gender = gender;
        this.email = email;
        this.user_password = user_password;
        this.occupation = occupation;
        this.local_office = local_office;
        this.sector = sector;
        this.access_level = access_level;
        this.photo_profile = photo_profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocal_office() {
        return local_office;
    }

    public void setLocal_office(String local_office) {
        this.local_office = local_office;
    }

    public SectorEnum getSector() {
        return sector;
    }

    public void setSector(SectorEnum sector) {
        this.sector = sector;
    }

    public AccessLevel getAccess_level() {
        return access_level;
    }

    public void setAccess_level(AccessLevel access_level) {
        this.access_level = access_level;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }
}
