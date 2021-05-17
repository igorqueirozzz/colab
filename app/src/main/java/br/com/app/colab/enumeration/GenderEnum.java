package br.com.app.colab.enumeration;

import br.com.app.colab.enumeration.api.IEnumModel;

public enum GenderEnum implements IEnumModel {
    MALE(1, "MASCULINO"),
    FEMALE(1,"FEMININO");

    private Integer value;
    private String description;

    GenderEnum(Integer value, String description) {
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
