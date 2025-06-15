package commons.dto;

import commons.models.Rezervare;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Rezervari extends AbstractDTO {
    private final static Rezervari instance = new Rezervari();

    public static Rezervari getInstance() {
        return instance;
    }


    private ArrayList<Rezervare> rezervari;

    public Rezervari() {

        rezervari = new ArrayList<>();
    }

    @Override
    protected void loadFS(Object source) throws UnsupportedOperationException, FileNotFoundException {
        String filePath = (String) source;
        File fileObj = Paths.get(filePath).toFile();
        Scanner scanner = new Scanner(fileObj);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            String id_rezervari = split[0];
            Integer cod_aventura = Integer.parseInt(split[1]);
            Integer nr_locuri = Integer.parseInt(split[2]);

            Rezervare r = new Rezervare(id_rezervari, cod_aventura, nr_locuri);

            addRezervare(r);
        }



    }

    @Override
    protected void loadDB(Object source) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    public void addRezervare(Rezervare r) {
        if(rezervari.contains(r)) {
            System.out.println("Rezervare already exists");
            return;
        }
        rezervari.add(r);
    }

    public ArrayList<Rezervare> getRezervari() {
        return rezervari;

    }

}
