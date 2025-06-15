package commons.models;

public class Rezervare {
    private String id_rezervare;
    private Integer cod_aventura;
    private Integer numar_locuri_rezervate;

    public Rezervare(String id_rezervare, Integer cod_aventura, Integer numar_locuri_rezervate) {
        this.id_rezervare = id_rezervare;
        this.cod_aventura = cod_aventura;
        this.numar_locuri_rezervate = numar_locuri_rezervate;
    }

    public String  getId_rezervare() {
        return id_rezervare;
    }

    public Integer getCod_aventura() {
        return cod_aventura;
    }

    public Integer getNumar_locuri_rezervate() {
        return numar_locuri_rezervate;
    }
}
