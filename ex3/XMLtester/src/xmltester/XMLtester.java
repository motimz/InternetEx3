/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltester;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import myXmlParser.BookBean;
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
        // tell the parser to use  handler
        parser.setContentHandler(handler);
        // read and parse the document
        parser.parse("book.xml");
        List<BookBean> books=handler.getBooks();
        System.out.println(books);
        }catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        }catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        }catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
