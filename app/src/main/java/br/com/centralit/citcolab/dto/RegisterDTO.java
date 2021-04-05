package br.com.centralit.citcolab.dto;

import java.util.Date;

import br.com.centralit.citcolab.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDTO {
    private Long user_id;
    private String user_location;
    private Date register_date;
    private Date register_time;
    private String reference;
    private User user;
}
