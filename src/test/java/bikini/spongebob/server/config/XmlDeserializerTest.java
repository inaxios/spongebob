package bikini.spongebob.server.config;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlDeserializerTest {

    private XmlDeserializer xmlDeserializer;

    @Before
    public void setup() {
        xmlDeserializer = XmlDeserializer.getInstance();
    }

    @Test
    public void testDeserializing() {
       String xml = "<spongebobconfig>\n" +
               "    <servlets>\n" +
               "        <servlet>\n" +
               "            <servletname>servletbat</servletname>\n" +
               "            <servletclass>servlet.ServletBat</servletclass>\n" +
               "        </servlet>\n" +
               "        <servlet>\n" +
               "            <servletname>servletbi</servletname>\n" +
               "            <servletclass>servlet.ServletBi</servletclass>\n" +
               "        </servlet>\n" +
               "    </servlets>\n" +
               "\n" +
               "    <servletmappings>\n" +
               "        <servletmapping>\n" +
               "            <servletname>servletbat</servletname>\n" +
               "            <urlpattern>/bat/*</urlpattern>\n" +
               "        </servletmapping>\n" +
               "\n" +
               "        <servletmapping>\n" +
               "            <servletname>servletbi</servletname>\n" +
               "            <urlpattern>/bi/*</urlpattern>\n" +
               "        </servletmapping>\n" +
               "    </servletmappings>\n" +
               "</spongebobconfig>";

        SpongeBobConfig config = xmlDeserializer.parseConfigTest(xml);

        assertEquals(2, config.getServlets().size());
        assertEquals(2, config.getServletmappings().size());
        assertEquals("servletbat", config.getServlets().get(0).getServletname());
        assertEquals("servlet.ServletBat", config.getServlets().get(0).getServletclass());
    }


}
