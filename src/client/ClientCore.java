package client;

import commons.AbstractMessage;

import java.io.*;
import java.net.Socket;

public class ClientCore {
    private String host="localhost";
    private int port=8080;
    private Socket socket;
    private Boolean isRunning=false;

    private ObjectInputStream in;
    private PrintWriter output;

    public ClientCore(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        connect();
    }

    public ClientCore() throws IOException {
        connect();
    }

    private void connect() throws IOException {
        this.socket=new Socket(host,port);
        System.out.println("Connected to "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
        this.in=new ObjectInputStream(socket.getInputStream());
        this.output=new PrintWriter(socket.getOutputStream(),true);

        this.isRunning=true;
    }


    private void writeOutput(Object message){
        AbstractMessage msg=(AbstractMessage)message;
        msg.display();
    }

    private void sendCommand(String command){
        output.println(command);
        output.flush();
    }

    private void readSocketInput() throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if(!(obj instanceof AbstractMessage)){
            System.out.println("Object is not an instance of AbstractMessage");
            return;
        }
        writeOutput(obj);
    }

    private String readFromSTDIN() throws IOException {
        System.out.print("\n> ");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        return console.readLine();
    }

    public void cli() throws IOException, ClassNotFoundException {
        if(!isRunning){
            return;
        }

        sendCommand("help");
        readSocketInput();

        while(isRunning){
            String input = readFromSTDIN();
            sendCommand(input);
            readSocketInput();
            if(input.toLowerCase().equals("exit")){
                isRunning=false;
                break;
            }

        }
    }
}
