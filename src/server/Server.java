package server;

import commons.FileDestionations;
import commons.HelpTable;
import commons.dto.Aventuri;
import commons.dto.Rezervari;

import java.io.FileNotFoundException;

public class Server {
    public static void main(String[] args) throws FileNotFoundException {
        loadStaticObjects();


        Rezervari rezervari = Rezervari.getInstance();
        rezervari.load(FileDestionations.FILE_SYSTEM,"rezervari.txt");

        Aventuri aventuri = Aventuri.getInstance();
        String aventuriPath= "aventuri.json";
        aventuri.load(FileDestionations.FILE_SYSTEM,aventuriPath);
        aventuri.display();

        //map rezervari to aventuri;
        aventuri.mapRezervariToAventuri(rezervari.getRezervari());




        ServerCore serverCore = new ServerCore();
        serverCore.init_server().serve();
    }


    private static void loadStaticObjects(){
        HelpTable t = HelpTable.getInstance();
        System.out.println("Help TABLE");
        t.display();
    }
}
