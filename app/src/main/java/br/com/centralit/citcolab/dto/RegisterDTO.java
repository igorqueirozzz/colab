package br.com.centralit.citcolab.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
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
@JsonAutoDetect
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = -2340861764386969945L;

    private Long user_id;
    private String user_location;
    private String register_date;
    private String register_time;
    private String reference;
    private User userEntity;
}
