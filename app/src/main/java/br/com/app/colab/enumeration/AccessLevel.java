package br.com.app.colab.enumeration;


import br.com.app.colab.enumeration.api.IEnumModel;

public enum AccessLevel implements IEnumModel {
    USER(0, "USUARIO"),
    ADMIN(1, "ADMINISTRADOR"),
    MASTER(2, "GERENCIAL");

    private Integer value;
    private String description;

    AccessLevel(Integer value, String description) {
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
