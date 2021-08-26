package bikini.spongebob.server;


public class Main {

    public static void main(String[] args) {
        SpongeBobServer server = new SpongeBobServer(8082);
        server.start();
    }
}
