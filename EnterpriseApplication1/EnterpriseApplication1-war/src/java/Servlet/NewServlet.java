/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.Book;
import beans.NewStatelessBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author win10
 */
public class NewServlet extends HttpServlet {

    @EJB
    private NewStatelessBeanLocal newStatelessBean;

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
            out.println("<title>Servlet S03_04Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (request.getParameter("insertBook") != null) {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                float price = Float.parseFloat(request.getParameter("price"));
                String publisher = request.getParameter("publisher");
                String picture = request.getParameter("picture");
                String returnStr = newStatelessBean.insertBook(title, author, price, publisher, picture);
                if (returnStr.equals("Success")) {
                    out.println("<h1>Insert Book Success</h1>");
                } else {
                    out.println("<h1>Insert Book Error</h1>");
                }
            } else if (request.getParameter("countBook") != null) {
                float price1 = Float.parseFloat(request.getParameter("price1"));
                float price2 = Float.parseFloat(request.getParameter("price2"));
                int returncount = newStatelessBean.countBook(price1, price2);
                out.println("<h1>Count Book: " + returncount + "</h1>");
            } else {
                String titleStartWith = request.getParameter("titleStartWith");
                List<Book> listBook = newStatelessBean.searchByName(titleStartWith);
                request.setAttribute("listBook", listBook);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listBook.jsp");
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

}
