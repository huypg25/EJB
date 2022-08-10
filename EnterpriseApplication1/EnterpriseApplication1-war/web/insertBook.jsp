<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Book Page</title>
    </head>
    <body>
        <h1>Insert Book</h1>
        <form action="S03_04Servlet" method="Post">
            Title:<input type="text" name="title"/><br>
            Author:<input type="text" name="author"/><br>
            Price:<input type="text" name="price"/><br>
            Publisher:<input type="text" name="publisher"/><br>
            Picture:<input type="text" name="picture"/><br><br>
            <input type="submit" name="insertBook" value="InsertBook">
        </form>
    </body>
</html>