/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex3;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Moti
 */
public class IdHandler extends DefaultHandler{
    private final ArrayList<BookBean> bookL;
    private String tmpValue;
    private BookBean bookTmp;
    private final String id;
    
    public IdHandler(String str) {
        id=str;
        bookL = new ArrayList<BookBean>();
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
            if(bookTmp.getId().equals(id))
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
}
