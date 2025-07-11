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

    <% if(cart == null || cart.getItems().isEmpty()){ %>

        <p> Sorry, there are no products in the shopping Cart</p>

    <%} else { %>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>quantity</th>
            <th>Total</th>
            <th>Delete</th>
        </tr>

        <%for(ItemCart item: cart.getItems()){%>
        <tr>
           <td><%=item.getProduct().getId()%></td>
           <td><%=item.getProduct().getName()%></td>
           <td><%=item.getProduct().getPrice()%></td>
           <td><input type="number" value="1"></td>
           <td><%=item.getTotal()%></td>
           <td><input type="checkbox" name="delete" value="<%=item.getProduct().getId()%>"></td>
        </tr>
        <%} %>

        <tr>
            <td colspan="4" style="text-align: right">Total:</td>
            <td><%=cart.getTotal()%></td>
        </tr>
    </table>
    <% }%>
    <p><a href="<%=request.getContextPath()%>/update-cart">Update</a></p>
    <p><a href="<%=request.getContextPath()%>/products">Continue Shopping</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Return</a></p>
</body>
</html>