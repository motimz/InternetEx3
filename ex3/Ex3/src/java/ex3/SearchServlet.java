/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author gilmi
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // --------- create the parser -------------

            // create keywords array     
            Double limit = Double.parseDouble((String)request.getParameter("searchPrice"));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Tell factory that the parser must understand namespaces
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();
            XMLReader parser;
            parser = saxParser.getXMLReader();
            // -------------------------------------
        
	    List<BookBean> results;
	    if (request.getParameter("id") != null)
	    {
                IdHandler idhandler = new IdHandler(request.getParameter("id"));
                parser.setContentHandler(idhandler);
                parser.parse(getServletContext().getRealPath("/") + "book.xml");
		// parse... replace new Array... with call to the parser with regular handler
		results = idhandler.getBooks();
	    }
	    else // parse with a different handler that find a book with specific ID
	    {
                String[] keywords = ((String)request.getParameter("searchKeywords")).split(" ");
                // Create a handler
                KeyPriceHandler kphandler = new KeyPriceHandler(keywords, limit);
                // tell the parser to use handler
                parser.setContentHandler(kphandler);
                // read and parse the document
                
                parser.parse(getServletContext().getRealPath("/") + "book.xml");
		// parse... replace new Array... with call to the parser with ID handler
		results = kphandler.getBooks();
	    }
            
            if (results.size() > 1)
            {
                request.setAttribute("books", results);
                request.getRequestDispatcher("MultiResultPage.jsp")
                    .forward(request, response);
                
            }
            else if (results.size() == 1)
            {
                request.setAttribute("book", results);
                request.getRequestDispatcher("SingleResultPage.jsp")
                    .forward(request, response);
            }
            else 
                request.getRequestDispatcher("NoResultPage.jsp")
                    .forward(request, response);
            
        }catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        }catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        }catch (IOException e) {
            System.out.println("IO error");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
