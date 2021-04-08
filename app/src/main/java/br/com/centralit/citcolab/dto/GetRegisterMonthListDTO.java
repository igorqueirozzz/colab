package br.com.centralit.citcolab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetRegisterMonthListDTO {

    private Long userId;
    private String reference;
}
