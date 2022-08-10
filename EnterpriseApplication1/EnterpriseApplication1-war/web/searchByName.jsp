<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search By Name Page</title>
    </head>
    <body>
        <h1>Search By Name</h1>
        <form action="S03_04Servlet" method="Post">
            Title Start With:<input type="text" name="titleStartWith"/><br><br>
            <input type="submit" name="titleStartWith" value="Search">
        </form>
    </body>
</html>