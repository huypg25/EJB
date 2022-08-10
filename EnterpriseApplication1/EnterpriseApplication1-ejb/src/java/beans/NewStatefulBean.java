/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Book;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author win10
 */
@Stateful
public class NewStatefulBean implements NewStatefulBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=EJB";
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String user_db = "sa";
    private static final String pass_db = "1234$";
    private List<Integer> listInteger;
    private List<Book> listBook;

    public NewStatefulBean() {
        listBook = new ArrayList<Book>();
        listInteger = new ArrayList<Integer>();
    }

    public List<Integer> getListInteger() {
        return listInteger;
    }

    public void setListInteger(List<Integer> listInteger) {
        this.listInteger = listInteger;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

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
    public void addBasket(int bookID) {
        listInteger.add(bookID);
    }

    @Override
    public List<Integer> getBasket() {
        return listInteger;
    }

    @Override
    public List<entity.Book> getAllBook() {
        Connection conn = null;
        listBook = new ArrayList<Book>();
        CallableStatement callableStatement = null;
        try {
            conn = getDBConnection();
            String getAllBook = "{call getAllBook()}";
            callableStatement = conn.prepareCall(getAllBook);
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

    @Override
    public List<entity.Book> getListBook() {
        return listBook;
    }
}
