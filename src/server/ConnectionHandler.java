package server;

import commons.AbstractMessage;
import commons.HelpTable;
import commons.Table;
import commons.TableResponseMessage;
import commons.dto.Aventuri;
import commons.models.Aventura;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private final HelpTable helpTable = HelpTable.getInstance();
    private final Socket socket;
    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    private TableResponseMessage<String> sendHelpTable(){
        return new TableResponseMessage<>("HELP",helpTable.getTable());
    }

    private TableResponseMessage<String> listAventuri(){
        Table<String> tab = Aventuri.getInstance().getAventuriTable();
        return new TableResponseMessage<>("LIST",tab);
    }

    private AbstractMessage readCommand(String input){

        String[] tokens = input.toLowerCase().split("\\s+");

        if(!helpTable.validateCommand(tokens[0])){
            return sendHelpTable();
        }

        if (tokens[0].equals("list")) {
            return listAventuri();
        }
        return sendHelpTable();
    }


    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String request;
            while ((request = in.readLine()) != null) {
                if("exit".equals(request.toLowerCase())){
                    out.writeObject(new TableResponseMessage<>("Good Bye!",null));
                    exit();
                    break;
                }
                AbstractMessage message = readCommand(request);
                out.writeObject(message);
                out.flush();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void exit(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
