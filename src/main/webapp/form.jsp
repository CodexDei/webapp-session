<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*, java.time.format.*, org.aguzman.apiservlet.webapp.headers.models.*"%>
<%
List<Category> categories = (List<Category>) request.getAttribute("categories");
Map<String,String> errors = (Map<String,String>) request.getAttribute("errors");
Product product = (Product) request.getAttribute("product");
String date = product.getRegistrationDate() != null?
product.getRegistrationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Form</title>
</head>
<body>
    <h1>Product Form</h1>
    <form action="<%=request.getContextPath()%>/products/form" method="post">
        <div>
            <label for="name">Name</label>
            <div>
                <input type="text" name="name" id="name" value="<%=product.getName() !=null ? product.getName() : ""%>">
            </div>
            <%if(errors != null && errors.containsKey("name")){%>
                <div style="color:red;"><%=errors.get("name")%></div>
            <% } %>
        </div>
        <div>
            <label for="price">Price</label>
            <div>
                <input type="number" name="price" id="price" value="<%=product.getPrice() > 0 ? product.getPrice() : ""%>">
            </div>
            <%if(errors != null && errors.containsKey("price")){%>
                <div style="color:red;"><%=errors.get("price")%></div>
            <% } %>
        </div>
        <div>
            <label for="sku">Sku</label>
            <div>
                <input type="text" name="sku" id="sku" value="<%=product.getSku() !=null ? product.getSku() : ""%>">
            </div>
            <%if(errors != null && errors.containsKey("sku")){%>
                <div style="color:red;"><%=errors.get("sku")%></div>
            <% } %>
        </div>
        <div>
            <label for="registration_date">Registration Date</label>
            <div>
                <input type="date" name="registration_date" id="registration_date" value="<%=date%>">
            </div>
            <%if(errors != null && errors.containsKey("registration_date")){%>
                <div style="color:red;"><%=errors.get("registration_date")%></div>
            <% } %>
        </div>
        <div>
            <label for="category">Category</label>
            <div>
                <select name="category" id="category">
                    <option value="">--- Select ----</option>
                    <%for(Category c : categories){%>
                    <option value="<%=c.getId()%>" <%=c.getId().equals(product.getCategory().getId())? "selected" : ""%> ><%=c.getName()%></option>
                    <%}%>

                </select>
            </div>
            <%if(errors != null && errors.containsKey("category")){%>
                <div style="color:red;"><%=errors.get("category")%></div>
            <% } %>
        </div>

        <div><input type="submit" value= "<%=(product.getId() != null && product.getId() > 0) ? "Edit" : "Create"%>"></div>
        <input type="hidden" name="id" value="<%=product.getId()%>">
    </form>

</body>
</html>