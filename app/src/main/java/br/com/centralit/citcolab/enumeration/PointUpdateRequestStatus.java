package br.com.centralit.citcolab.enumeration;

import br.com.centralit.citcolab.enumeration.api.IEnumModel;

public enum PointUpdateRequestStatus implements IEnumModel {

    PENDENTE(0,"PENDENTE"),
    VERIFICADA(1,"VERIFICADA");

    private String description;

    private Integer value;

    PointUpdateRequestStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }


}
