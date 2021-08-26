package bikini.spongebob.server;

import bikini.spongebob.server.config.SpongeBobConfig;
import bikini.spongebob.server.config.SpongeBobServletConfig;
import bikini.spongebob.server.config.SpongeBobServletMapping;
import javax.servlet.Servlet;
import java.io.*;
import java.net.Socket;

public class Conexion extends Thread{

    private SpongeBobConfig servletsConfig;
    private Socket socket;
    private DataOutputStream out;
    private BufferedReader in;

    public Conexion(SpongeBobConfig servletsConfig, Socket socket) {
        this.servletsConfig = servletsConfig;
        this.socket = socket;
        try {
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("\n\nWorking on petition received on port " + socket.getLocalPort() + " from " + socket.getRemoteSocketAddress());
        try {
            Request request = new Request(in);
            Response response = new Response(out);
            Servlet servlet = loadRelevantServlet(request.getUri());
            if(servlet == null) {
                System.out.println("No servlet found for uri " + request.getUri() + ", so not sending anything back");
                return;
            }
            servlet.service(request, response);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private Servlet loadRelevantServlet(String uri) {
        String wantedServletName = findTheNameOfTheServletToUse(uri);
        for(SpongeBobServletConfig spongeBobServletConfig : servletsConfig.getServlets()) {
            if(spongeBobServletConfig.getServletname().equals(wantedServletName)) {
                try {
                    return (Servlet)getClass().getClassLoader().loadClass(spongeBobServletConfig.getServletclass()).newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String findTheNameOfTheServletToUse(String uri) {
        for(SpongeBobServletMapping mapping : servletsConfig.getServletmappings()) {
            if(mapping.getUrlpattern().startsWith(uri)) {
                return mapping.getServletname();
            }
        }
        return null;
    }
}
