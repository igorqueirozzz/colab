package br.com.centralit.citcolab.dto;



public class GetRegisterMonthListDTO {

    private Long userId;
    private String reference;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
