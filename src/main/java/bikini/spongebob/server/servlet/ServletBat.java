package bikini.spongebob.server.servlet;

import bikini.spongebob.server.Response;
import javax.servlet.*;
import java.io.IOException;

public class ServletBat implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        Response response = (Response)servletResponse;
        response.getOutput().writeBytes("HTTP/1.1 200 OK\r\n");
        response.getOutput().writeBytes("Content-Type: text/html\r\n\r\n");
        response.getOutput().writeBytes("<html><head></head><body><h1>Hi from servlet one! zer nahi duzue? :)</h1></body></html>");
  }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
