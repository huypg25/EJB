/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Entities.Book;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author win10
 */
@Stateless
public class NewStatelessBean implements NewStatelessBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=EJB";
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String user_db = "sa";
    private static final String pass_db = "1234$";

    private static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, user_db, pass_db);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    @Override
    public String insertBook(String title, String author, float price, String publisher, String picture) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = getDBConnection();
            String insertBook = "{call insertBook(?,?,?,?,?)}";
            callableStatement = conn.prepareCall(insertBook);
            callableStatement.setString(1, title);
            callableStatement.setString(2, author);
            callableStatement.setFloat(3, price);
            callableStatement.setString(4, publisher);
            callableStatement.setString(5, picture);
            callableStatement.executeUpdate();
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @Override
    public int countBook(float price1, float price2) {
        Connection conn = null;
        CallableStatement callableStatement = null;
        int countB = 0;
        try {
            conn = getDBConnection();
            String countBook = "{call countBook(?,?,?)}";
            callableStatement = conn.prepareCall(countBook);
            callableStatement.setFloat(1, price1);
            callableStatement.setFloat(2, price2);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();
            countB = callableStatement.getInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countB;
    }

    @Override
    public List<Book> searchByName(String titleStartWith) {
        String st = titleStartWith + "%";
        Connection conn = null;
        CallableStatement callableStatement = null;
        List<Book> listBook = new ArrayList<Book>();
        try {
            conn = getDBConnection();
            String searchByName = "{call searchByName(?)}";
            callableStatement = conn.prepareCall(searchByName);
            callableStatement.setString(1, st);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setPrice(rs.getFloat("Price"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPicture(rs.getString("Picture"));
                listBook.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBook;
    }
}
