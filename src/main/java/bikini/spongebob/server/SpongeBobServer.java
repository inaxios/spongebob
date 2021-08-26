package bikini.spongebob.server;

import bikini.spongebob.server.config.SpongeBobConfig;
import bikini.spongebob.server.config.XmlDeserializer;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SpongeBobServer {

    private int port;
    private ServerSocket serverSocket;

    public SpongeBobServer(int port) {
        this.port = port;
    }

    public void start() {
        try{
            SpongeBobConfig servletsConfig = loadServletsFromConfigFile();
            serverSocket = new ServerSocket(port);//edo new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
            System.out.println( "SpongeBob listening on port " + port);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                Thread.sleep(100);
                Connexion con = new Connexion(servletsConfig, clientSocket);
                con.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SpongeBobConfig loadServletsFromConfigFile() {
        System.out.print("Loading servlet config... ");
        SpongeBobConfig config = XmlDeserializer.getInstance().parseConfigFile();
        System.out.println("OK. Found " + config.getServlets().size() + " servlets");
        return config;
    }
}
