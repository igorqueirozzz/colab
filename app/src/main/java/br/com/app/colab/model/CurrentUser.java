package br.com.app.colab.model;


import br.com.app.colab.enumeration.AccessLevel;
import br.com.app.colab.enumeration.GenderEnum;
import br.com.app.colab.enumeration.SectorEnum;

public class CurrentUser {

    private static Long id;
    private static Long employerId;
    private static String cpf;
    private static String user_name;
    private static GenderEnum gender;
    private static String email;
    private static String occupation;
    private static String local_office;
    private static SectorEnum sector;
    private static AccessLevel access_level;
    private static String photo_profile;

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        CurrentUser.id = id;
    }

    public static Long getEmployerId() {
        return employerId;
    }

    public static void setEmployerId(Long employerId) {
        CurrentUser.employerId = employerId;
    }

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        CurrentUser.cpf = cpf;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        CurrentUser.user_name = user_name;
    }

    public static GenderEnum getGender() {
        return gender;
    }

    public static void setGender(GenderEnum gender) {
        CurrentUser.gender = gender;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        CurrentUser.email = email;
    }

    public static String getOccupation() {
        return occupation;
    }

    public static void setOccupation(String occupation) {
        CurrentUser.occupation = occupation;
    }

    public static String getLocal_office() {
        return local_office;
    }

    public static void setLocal_office(String local_office) {
        CurrentUser.local_office = local_office;
    }

    public static SectorEnum getSector() {
        return sector;
    }

    public static void setSector(SectorEnum sector) {
        CurrentUser.sector = sector;
    }

    public static AccessLevel getAccess_level() {
        return access_level;
    }

    public static void setAccess_level(AccessLevel access_level) {
        CurrentUser.access_level = access_level;
    }

    public static String getPhoto_profile() {
        return photo_profile;
    }

    public static void setPhoto_profile(String photo_profile) {
        CurrentUser.photo_profile = photo_profile;
    }
}
