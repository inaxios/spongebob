package bikini.spongebob.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SpongeBobServerTest {

    private SpongeBobServer spongeBobServer;

    @Before
    public void setup() {
        spongeBobServer = new SpongeBobServer(8082);
        spongeBobServer.start();
    }

    @After
    public void tearDown() {
        spongeBobServer.stop();
    }

    @Test
    @Ignore
    public void testBlah() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8082);
        String request = "GET /bat HTTP/1.1";

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(request);
        //out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        assertEquals("una de bravas", in.readLine());
        in.close();
        out.close();
    }
}
