package br.com.app.colab.enumeration;


import br.com.app.colab.enumeration.api.IEnumModel;

public enum SectorEnum implements IEnumModel {

    ADMIN(0, "ADMINISTRATIVO"),
    MANAGER(1, "GERENCIA"),
    IT(2, "TECNOLOGIA DA INFORMAÇÃO");

    private String description;
    private Integer value;

    SectorEnum( Integer value, String description) {
        this.description = description;
        this.value = value;
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
