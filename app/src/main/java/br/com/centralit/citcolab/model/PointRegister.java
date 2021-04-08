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
    private String register_local;
    private Date register_time;
    private String reference;
    private User user_id;

}