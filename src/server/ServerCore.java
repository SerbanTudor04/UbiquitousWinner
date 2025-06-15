package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerCore {
    private ServerSocket server;
    private int port = 8080;
    public static boolean is_running=false;

    public ServerCore(int port) {
        this.port = port;
    }

    public ServerCore() {
    }
    public ServerCore init_server(){
        try{
            server = new ServerSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public void serve() {
        System.out.println("Listening on port " + port);
        is_running=true;
        try{
            while(is_running){
                new Thread(new ConnectionHandler(server.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server restarting...");
            serve();
        }
    }

}
