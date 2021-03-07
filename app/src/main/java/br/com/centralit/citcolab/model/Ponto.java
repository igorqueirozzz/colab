package br.com.centralit.citcolab.model;

public class Ponto {

    private String date, local, hora;

    public Ponto(String date, String local, String hora) {
        this.date = date;
        this.local = local;
        this.hora = hora;
    }

    public String getDate() {
        return date;
    }

    public String getLocal() {
        return local;
    }

    public String getHora() {
        return hora;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
