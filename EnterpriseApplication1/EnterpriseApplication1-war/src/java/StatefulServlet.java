/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.NewStatefulBeanRemote;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author win10
 */
public class StatefulServlet extends HttpServlet {

    NewStatefulBeanRemote newStatefulBean = lookupNewStatefulBeanRemote();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatefulServlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (request.getParameter("bookID") != null) {
                int bookID = Integer.parseInt(request.getParameter("bookID"));
                newStatefulBean.addBasket(bookID);
                List<Book> listBook = newStatefulBean.getListBook();
                List<Integer> listInteger = newStatefulBean.getBasket();
                request.setAttribute("listBook", listBook);
                request.setAttribute("listInteger", listInteger);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/getAllBook.jsp");
                rd.forward(request, response);
            } else {
                List<Book> listBook = newStatefulBean.getAllBook();
                request.setAttribute("listBook", listBook);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/getAllBook.jsp");
                rd.forward(request, response);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    private NewStatefulBeanRemote lookupNewStatefulBeanRemote() {
        try {
            Context c = new InitialContext();
            return (NewStatefulBeanRemote) c.lookup("java:global/EnterpriseApplication1/EnterpriseApplication1-ejb/NewStatefulBean!beans.NewStatefulBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
