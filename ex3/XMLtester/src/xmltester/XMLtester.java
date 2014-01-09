/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import myXmlParser.BookBean;
import myXmlParser.IdHandler;
import myXmlParser.KeyPriceHandler;
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
            
        // create keywords array
        ArrayList<String> keywords = new ArrayList<>();
        
        for (int i = 1; i < args.length; i++)
            keywords.add(args[i]);
        
        Double limit = Double.parseDouble(args[0]);
        SAXParserFactory factory = SAXParserFactory.newInstance();      
        // Tell factory that the parser must understand namespaces       
        factory.setNamespaceAware(true);
        SAXParser saxParser = factory.newSAXParser();
        XMLReader parser;
        parser = saxParser.getXMLReader();
        // Create a handler
        KeyPriceHandler kphandler = new KeyPriceHandler(keywords,limit);
        IdHandler idhandler = new IdHandler("bk108");
        // tell the parser to use  handler
        parser.setContentHandler(idhandler);
        // read and parse the document
        parser.parse("book.xml");
        List<BookBean> books=idhandler.getBooks();
        System.out.println(books);
        }catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        }catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        }catch (IOException e) {
            System.out.println("IO error");
        }
         catch(NumberFormatException e)
        {
            System.err.println("Bad limit number");
        }
    }
}
