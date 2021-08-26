package bikini.spongebob.server.servlet;

import bikini.spongebob.server.Response;

import javax.servlet.*;
import java.io.IOException;

public class ServletThree implements Servlet {

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
        response.getOutput().writeBytes("Hi from servlet three. Don't expect to see this in a browser :)");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
