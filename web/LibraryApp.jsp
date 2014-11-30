<%@ page import="shared.model.vo.Book" %>
<%@ page import="shared.utils.UtilityConstants" %>
<%@ page import="java.util.List" %>
<%@ page import="shared.model.vo.OrderEntry" %>
<%--
    Document   : LibraryApp
    Author     : Katya Atamanchuk
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="Notus Internet provider"/>
    <title>LibraryApp</title>
    <link rel="icon" href="favicon.ico"/>
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Caption' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
</head>
<body>
<div>
        <div>
            <% if (session.getAttribute(UtilityConstants.USER) == null) { %>
            <form method="POST" action="./" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input required type="text" name="login"  placeholder="User" class="form-control">
                </div>
                <div class="form-group">
                    <input required type="password" name="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary" value="ServerServlet">Login</button>
            </form>
            <% } %>

        <%  List<Book> bookCatalog = (List<Book>) session.getAttribute(UtilityConstants.BOOKS);
            if (bookCatalog != null) {
                if (bookCatalog.isEmpty()) { %>
                    <p>Sorry, but currently there are no books in the library</p>
            <%  } else { %>
            <table class='activeInstances table table-striped table-hover'>
                <thead>

                    <tr>
                        <td>Title</td>
                        <td>Authors</td>
                        <td>Category</td>
                        <td>Description</td>
                        <td>Rating</td>
                        <td>Number of pages</td>
                    </tr>
                </thead>

                <% for (Book book: bookCatalog) { %>
                <tbody>

                    <tr>

                           <td><%=book.getTitle()%></td>
                           <td><%=book.getAuthors()%></td>
                           <td><%=book.getCategory()%></td>
                           <td><%=book.getDescription()%></td>
                           <td><%=book.getRating()%></td>
                           <td><%=book.getNumberOfPages()%></td>
                       </tr>
                </tbody>

                <% } %>
                </table>
               <% } %>
            <% } %>


        <%  List<OrderEntry> orders = (List<OrderEntry>) session.getAttribute(UtilityConstants.ORDERS);
            if (orders != null) {
                if (orders.isEmpty()) { %>
            <p>Currently there are no book orders</p>
            <%  } else { %>
                <% for (OrderEntry order: orders) { %>
            <table class='activeInstances table table-striped table-hover'>

            <tr>
                        <td width="200">Order #<%=order.getId()%></td>
                        <td width="200">User ID: <%=order.getUserId()%></td>
                        <td width="200">Order date: <%=order.getWaitingSince()%></td>
                    </tr>
            </table>
            <table class='activeInstances table table-striped table-hover'>
                <thead>
                <tr>
                    <td width="200">Title</td>
                    <td width="200">Authors</td>
                    <td width="200">Category</td>
                    <td width="200">Description</td>
                    <td width="200">Rating</td>
                    <td width="200">Number of pages</td>
                </tr>
                </thead>
                <tbody>

                <% for (Book book: order.getBooks()) { %>

                <tr>

                    <td><%=book.getTitle()%></td>
                    <td><%=book.getAuthors()%></td>
                    <td><%=book.getCategory()%></td>
                    <td><%=book.getDescription()%></td>
                    <td><%=book.getRating()%></td>
                    <td><%=book.getNumberOfPages()%></td>
                </tr>
                <% } %>

                </tbody>
                <% } %>
            </table>
            <% } %>
        <% } %>
    </div>
</div>

</body>
</html>

