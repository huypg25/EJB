/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Entities.Book;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author win10
 */
@Local
public interface NewStatelessBeanLocal {

    String insertBook(String title, String author, float price, String publisher, String picture);

    int countBook(float price1, float price2);

    List<Book> searchByName(String titleStartWith);
}
