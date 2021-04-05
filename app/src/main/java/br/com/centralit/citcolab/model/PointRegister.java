package br.com.centralit.citcolab.model;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PointRegister {

    private Long id;
    private Date register_date;
    private Date register_time;
    private String register_local;
    private User user_id;
    private String reference;

}