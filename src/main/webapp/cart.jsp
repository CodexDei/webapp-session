<%-- Directiva --%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="org.aguzman.apiservlet.webapp.headers.models.*"%>

<%
Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
</head>
<body>
    <h1>Shopping Cart</h1>

    <% if(cart == null || cart.isEmpty()){ %>

        <p> Sorry, there are no products in the shopping Cart</p>

    <%} else { %>

        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>quantity</th>
            <th>Total</th>
        </tr>

        <% for(ItemCart item: cart.getItems()) {%>
        <tr>
           <td><%=item.getProduct().getId()%></td>
           <td><%=item.getProduct().getName()%></td>
           <td><%=item.getProduct().getPrice()%></td>
           <td><%=item.getQuantity()%></td>
           <td><%=item.getTotal()%></td>
        </tr>
        <%} %>

        <tr>
            <td colspan="4" style="text-align: right">Total:</td>
            <td><%=carro.getTotal()%><td>
        </tr>

    <% }%>
</body>
</html>