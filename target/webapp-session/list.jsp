<%@page contentType="UTF-8" import="java.util.*, org.aguzman.apiservlet.webapp.headers.models.*"%>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    String AppMessage = (String) getServletContext().getAttribute("message");
    String RequestMessage = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<% if(username.isPresent()){ %>
    <div>Hello <%=username.get()%>!!, Welcome!!</div>
    <p><a href="<%=request.getContextPath()%>/products/form">Create [+]</a></p>
<% } %>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>type</th>
        <% if(username.isPresent()){ %>
        <th>price</th>
        <th>add</th>
        <th>edit</th>
        <% } %>
    </tr>
    <% for(Product p: products){ %>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getName()%></td>
        <td><%=p.getCategory().getName()%></td>
        <% if(username.isPresent()){ %>
        <td><%=p.getPrice()%></td>
        <td><a href="<%=request.getContextPath()%>/cart/add?id=<%=p.getId()%>">Add Cart</a></td>
        <td><a href="<%=request.getContextPath()%>/products/form?id=<%=p.getId()%>">Edit</a></td>
        <% } %>
    </tr>
    <%}%>
</table>
<p><%=AppMessage%></p>
<p><%=RequestMessage%></p>
</body>

</html>