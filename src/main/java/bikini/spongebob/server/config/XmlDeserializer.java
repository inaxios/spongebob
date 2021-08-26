package bikini.spongebob.server.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public class XmlDeserializer {

    private XStream xstream;
    private static final XmlDeserializer instance = new XmlDeserializer();

    private XmlDeserializer() {
        xstream = new XStream(new StaxDriver());
        init();
    }

    public static XmlDeserializer getInstance() {
        return instance;
    }

    private void init() {
        xstream.alias("spongebobconfig", SpongeBobConfig.class);
        xstream.alias("servlet", SpongeBobServletConfig.class);
        xstream.alias("servletclass", String.class);
        xstream.alias("servletname", String.class);
        xstream.alias("servletmapping", SpongeBobServletMapping.class);
        xstream.alias("urlmapping", String.class);
    }

    public SpongeBobConfig parseConfigFile() {//todo aldanin ez bota hau
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("web.xml");
        if (resource == null) {
            throw new IllegalArgumentException("file not found! ");
        } else {
            try {
                return (SpongeBobConfig) xstream.fromXML(new File(resource.toURI()));
            } catch (URISyntaxException e) {
                return null;
            }
        }
    }

    protected SpongeBobConfig parseConfigTest(String xml) {
        return (SpongeBobConfig)xstream.fromXML(xml);
    }
}
