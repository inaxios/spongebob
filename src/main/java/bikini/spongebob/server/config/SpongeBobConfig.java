package bikini.spongebob.server.config;


import java.util.List;

public class SpongeBobConfig {

    private List<SpongeBobServletConfig> servlets;
    private List<SpongeBobServletMapping> servletmappings;

    public List<SpongeBobServletConfig> getServlets() {
        return servlets;
    }

    public void setServlets(List<SpongeBobServletConfig> servlets) {
        this.servlets = servlets;
    }

    public List<SpongeBobServletMapping> getServletmappings() {
        return servletmappings;
    }

    public void setServletmappings(List<SpongeBobServletMapping> servletmappings) {
        this.servletmappings = servletmappings;
    }
}
