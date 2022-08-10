<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Count Book Page</title>
    </head>
    <body>
        <h1>Count Book</h1>
        <form action="S03_04Servlet" method="Post">
            Price From:<input type="text" name="price1"/><br>
            Price To:<input type="text" name="price2"/><br><br>
            <input type="submit" name="countBook" value="CountBook">
        </form>
    </body>
</html>