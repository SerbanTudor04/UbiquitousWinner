package commons.dto;

import commons.Table;
import commons.models.Aventura;
import commons.models.Rezervare;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Aventuri  extends  AbstractDTO{
    private static final Aventuri instance = new Aventuri();

    public static Aventuri getInstance() {
        return instance;
    }

    private ArrayList<Aventura> aventuri;
    private Table<String> aventuriTable;
    public Aventuri() {
        aventuri = new ArrayList<>();
        aventuriTable = new Table<>();
        List<String> headers = new ArrayList<>();
        headers.add("Cod Aventura");
        headers.add("Denumire");
        headers.add("Tarif");
        headers.add("Locuri Disponibile");
        aventuriTable.setHeaders(headers)
                .calculateWidths();


    }


    @Override
    protected void loadDB(Object source){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    protected void loadFS(Object source) {
        String filePath = (String) source;
        File fileObj = Paths.get(filePath).toFile();
        String content;
        try{
             content= new String(Files.readAllBytes(fileObj.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JSONArray jsonArray = new JSONArray(content);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Aventura a = new Aventura();
            a.setCod_aventura(jsonObject.getInt("cod_aventura"))
                    .setNumar_locuri(jsonObject.getInt("locuri_disponibile"))
                    .setTarif(jsonObject.getFloat("tarif"))
                    .setNume(jsonObject.getString("denumire"))
            ;

            addAventura(a);
        }

    }

    private Boolean addAventura(Aventura a){
        if(aventuri.contains(a)){
            System.out.printf("Aventura %s already exists.\n", a);
            return false;
        }
        aventuri.add(a);
        ArrayList<String> tableRow = new ArrayList<>();
        String codAventuraStr= String.valueOf(a.getCod_aventura());
        String denumireStr= String.valueOf(a.getNume());
        String tarifStr= String.valueOf(a.getTarif());
        String locuriDisponibileStr= String.valueOf(a.getNumar_locuri());

        tableRow.add(codAventuraStr);
        tableRow.add(denumireStr);
        tableRow.add(tarifStr);
        tableRow.add(locuriDisponibileStr);

        return aventuriTable.addRow(tableRow);

    }

    public Aventura findByCodAventura(int codAventura){
        for (Aventura a : aventuri) {
            if(a.getCod_aventura() == codAventura){
                return a;
            }
        }
        return null;
    }

    public void display(){
        aventuriTable.display();
    }


    public void mapRezervariToAventuri(@NotNull ArrayList<Rezervare> rezervari){
        for(Rezervare r : rezervari){
            Integer codAventura = r.getCod_aventura();
            Aventura aventura = findByCodAventura(codAventura);
            if(aventura != null){
                aventura.addRezervare(r);
            }
        }
    }

    public Table<String> getAventuriTable() {
        return aventuriTable;
    }

}
