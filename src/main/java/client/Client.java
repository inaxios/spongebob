package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8082);
        //zerbait bidali spongebob-i :)
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GET /bi HTTP/1.1");

        while(in.ready()) {
            System.out.println(in.readLine());
        }

        out.close();
        in.close();
        socket.close();
    }
}
