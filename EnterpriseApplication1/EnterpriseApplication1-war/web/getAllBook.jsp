<%@page import="java.util.ArrayList"%>
<%@page import="entity.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Object sessionObj = request.getSession().getAttribute("listBook");
    List<Book> listBook = new ArrayList<Book>();
    listBook = (List<Book>) sessionObj;
    Object sessionObjInt = request.getSession().getAttribute("listInteger");
    List<Integer> listInteger = new ArrayList<Integer>();
    listInteger = (List<Integer>) sessionObjInt;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Books</title>
    </head>
    <body>
        <div>
            <table border="1" cellpadding="5">
                <caption><h2>List of Books</h2></caption>
                <tr>
                    <th>BookID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Publisher</th>
                    <th>Picture</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="book" items="${listBook}">
                    <tr>
                        <td><c:out value="${book.bookID}" /></td>
                        <td><c:out value="${book.title}" /></td>
                        <td><c:out value="${book.author}" /></td>
                        <td><c:out value="${book.price}" /></td>
                        <td><c:out value="${book.publisher}" /></td>
                        <td><c:out value="${book.picture}" /></td>
                        <td>
                            <a href="StatefulServlet?bookID=${book.bookID}"> Add Basket </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <h1>List Books were added Basket:
                <c:forEach var="bookID" items="${listInteger}">
                    <c:out value="${bookID}" />;
                </c:forEach>
            </h1>
        </div>
    </body>
</html>