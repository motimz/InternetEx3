/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltester;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import myXmlParser.Handler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author motimi
 */
public class XMLtester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        SAXParserFactory factory = SAXParserFactory.newInstance();      
        // Tell factory that the parser must understand namespaces       
        factory.setNamespaceAware(true);
        SAXParser saxParser = factory.newSAXParser();
        XMLReader parser;
        parser = saxParser.getXMLReader();
        // Create a handler
        Handler handler = new Handler();
        // Tell the parser to use this handler
        parser.setContentHandler(handler);
        // Finally, read and parse the document
        parser.parse("hello.xml");
        }
        catch(Exception e){}
    }
}
