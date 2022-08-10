/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Book;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author win10
 */
@Remote
public interface NewStatefulBeanRemote {

    List<Book> getAllBook();

    void addBasket(int bookID);

    List<Integer> getBasket();

    List<Book> getListBook();
}
