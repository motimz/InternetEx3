/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myXmlParser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author motimi
 */
public class Handler extends DefaultHandler {
    private final List<BookBean> bookL;
    private String tmpValue;
    private BookBean bookTmp;
    private final ArrayList<String> keywords;
    private final double limit_price;
    
    public Handler(ArrayList<String> key,double limit) {
        keywords=key;
        limit_price=limit;
        bookL = new ArrayList<>();
    }

   @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {

        // if current element is book , create new book
        // clear tmpValue on start of element
        tmpValue="";
        if (elementName.equalsIgnoreCase("book")) {
            bookTmp = new BookBean();
            bookTmp.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        // if end of book element add to list
        if (element.equals("book")) {
            if(bookTmp.getPrice()<limit_price && checkKeyWords())
                bookL.add(bookTmp);
        }

        if (element.equalsIgnoreCase("author")) {
            bookTmp.setAuthor(tmpValue);
        }

        if (element.equalsIgnoreCase("title")) {

            bookTmp.setTitle(tmpValue);

        }

        if(element.equalsIgnoreCase("genre")){

           bookTmp.setGenre(tmpValue);

        }

        if(element.equalsIgnoreCase("price")){

            bookTmp.setPrice(Double.parseDouble(tmpValue));

        }

        if(element.equalsIgnoreCase("publish_date")){
                bookTmp.setDate(tmpValue);
        }
        
        if(element.equalsIgnoreCase("description")){
                bookTmp.setDescription(tmpValue);
        }
    }

    @Override

    public void characters(char[] ac, int start, int length) throws SAXException {
       tmpValue += new String(ac, start, length);
       tmpValue= tmpValue.replaceAll("\\s+", " ");
    }
    
    public List<BookBean> getBooks(){
        return bookL;   
    }
    
    private boolean checkKeyWords()
    {
        for(String keyword : keywords)
        {
            if(!bookTmp.getTitle().contains(keyword) &&
               !bookTmp.getDescription().contains(keyword) &&
               !bookTmp.getAuthor().contains(keyword))
                return false;
        }
        return true;
    }
    
}
