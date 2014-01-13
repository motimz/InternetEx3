/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author motimi
 */
public class KeyPriceHandler extends DefaultHandler {
    private final ArrayList<BookBean> bookL;
    private String tmpValue;
    private BookBean bookTmp;
    private final String[] keywords;
    private final Double limit_price;
    
    public KeyPriceHandler(String[] key, Double limit) {        
        limit_price=limit;
        bookL = new ArrayList<BookBean>();
        this.keywords = key;
        if (keywords != null)
        {
            for (int i = 0; i < keywords.length; ++i)
                keywords[i] = keywords[i].toLowerCase();
        }
    }

   @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {

        // if current element is book , create new book
        // clear tmpValue on start of element
        tmpValue="";
        if (qualifiedName.equalsIgnoreCase("book")) {
            bookTmp = new BookBean();
            bookTmp.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
        // if end of book element add to list
        if (qualifiedName.equals("book")) {
            if(checkPrice() && checkKeyWords())
                bookL.add(bookTmp);
        }

        if (qualifiedName.equalsIgnoreCase("author")) {
            bookTmp.setAuthor(tmpValue);
        }

        if (qualifiedName.equalsIgnoreCase("title")) {

            bookTmp.setTitle(tmpValue);

        }

        if(qualifiedName.equalsIgnoreCase("genre")){

           bookTmp.setGenre(tmpValue);

        }

        if(qualifiedName.equalsIgnoreCase("price")){

            bookTmp.setPrice(Double.parseDouble(tmpValue));

        }

        if(qualifiedName.equalsIgnoreCase("publish_date")){
                bookTmp.setDate(tmpValue);
        }
        
        if(qualifiedName.equalsIgnoreCase("description")){
                bookTmp.setDescription(tmpValue);
        }
    }

    @Override

    public void characters(char[] ch, int start, int length) throws SAXException {
       tmpValue += new String(ch, start, length);
       tmpValue= tmpValue.replaceAll("\\s+", " ");
    }
    
    public ArrayList<BookBean> getBooks(){
        return bookL;   
    }
    
    private boolean checkKeyWords()
    {
        if (keywords != null)
            for(String keyword : keywords)
            {
                if(!bookTmp.getTitle().toLowerCase().contains(keyword) &&
                   !bookTmp.getDescription().toLowerCase().contains(keyword))
                    return false;
            }
        
        return true;
    }
    private boolean checkPrice()
    {
        if (limit_price == null)
            return true;
        
        return (bookTmp.getPrice()<limit_price);
    }
}
