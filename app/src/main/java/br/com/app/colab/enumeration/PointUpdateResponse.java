package br.com.app.colab.enumeration;


import br.com.app.colab.enumeration.api.IEnumModel;

public enum PointUpdateResponse  implements IEnumModel {
    APROVED(0, "APROVADO"),
    RECUSED(1, "RECUSADO");

    private String description;
    private Integer value;

    PointUpdateResponse(int value, String description){
        this.description = description;
        this.value = value;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
