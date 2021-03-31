package br.com.centralit.citcolab.model;

import java.util.Calendar;

import br.com.centralit.citcolab.enumeration.AccessLevel;
import br.com.centralit.citcolab.enumeration.GenderEnum;
import br.com.centralit.citcolab.enumeration.SectorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private Long employerId;
    private String cpf;
    private String userName;
    private GenderEnum gender;
    private String email;
    private String userPassword;
    private String occupation;
    private String local_office;
    private SectorEnum sector;
    private AccessLevel accessLevel;
    private String photo_profile_url;

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
}
