package commons.models;

import java.util.ArrayList;
import java.util.Objects;

public class Aventura {
    private Integer cod_aventura;
    private String nume;
    private Float tarif;
    private Integer numar_locuri;
    private ArrayList<Rezervare> rezervari;
    private Integer locuri_disponibile;

    public Aventura() {
        rezervari = new ArrayList<>();
        locuri_disponibile = 0;
    }




    public boolean addRezervare(Rezervare rezervare) {
        if(rezervare == null){
            System.out.println("Rezervare is null!");
            return false;
        }
        if(!rezervare.getCod_aventura().equals(this.cod_aventura)){
            System.out.println("This rezervare doesn't have the same cod_aventura!");
            return false;
        }

        if(numar_locuri == null){
            numar_locuri=0;
        }

        if(Objects.equals(numar_locuri, locuri_disponibile)){
            System.out.println("There isn't any slots available!");
            return false;
        }
        Integer locuriRezervate = rezervare.getNumar_locuri_rezervate();


        if(locuri_disponibile+ locuriRezervate > numar_locuri){
            System.out.println("The currently locuri rezervate + locuri already occupied overflows the allocated locuri disponibile!");
            return false;
        }

        rezervari.add(rezervare);

        locuri_disponibile += locuriRezervate;

        return true;
    }


    public boolean removeRezervare(Rezervare rezervare) {
        if(rezervare == null){
            System.out.println("Rezervare is null!");
            return false;
        }

        if(!rezervari.remove(rezervare)){
            System.out.println("Rezervare doesn't exists in this lists!");
            return false;
        };
        return true;
    }

    public Integer getNumar_locuri() {
        return numar_locuri;
    }

    public Aventura setNumar_locuri(Integer numar_locuri) {
        this.numar_locuri = numar_locuri;
        return this;
    }

    public Float getTarif() {
        return tarif;
    }

    public Aventura setTarif(Float tarif) {
        this.tarif = tarif;
        return this;
    }

    public String getNume() {
        return nume;
    }

    public Aventura setNume(String nume) {
        this.nume = nume;
        return this;
    }

    public Integer getCod_aventura() {
        return cod_aventura;
    }

    public Aventura setCod_aventura(Integer cod_aventura) {
        this.cod_aventura = cod_aventura;
        return this;
    }

    @Override
    public String toString() {
        return "Aventura{" +
                "cod_aventura=" + cod_aventura +
                ", nume='" + nume + '\'' +
                ", tarif=" + tarif +
                ", numar_locuri=" + numar_locuri +
                ", rezervari=[" + rezervari +"]"+
                ", locuri_disponibile=" + locuri_disponibile +
                '}';
    }
}
