<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*, org.aguzman.apiservlet.webapp.headers.models.*"%>
<%
List<Category> categories = (List<Category>) request.getAttribute("categories");
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
                <input type="text" name="name" id="name" required>
            </div>
        </div>
        <div>
            <label for="price">Price</label>
            <div>
                <input type="number" name="prince" id="price" required>
            </div>
        </div>
        <div>
            <label for="sku">Sku</label>
            <div>
                <input type="text" name="sku" id="sku">
            </div>
        </div>
        <div>
            <label for="registration_date">Registration Date</label>
            <div>
                <input type="date" name="registration_date" id="registration_date">
            </div>
        </div>
        <div>
            <label for="category">Category</label>
            <div>
                <select name="category" id="category">
                    <option value="">--- Select ----</option>
                    <%for(Category c : categories){%>
                    <option value="<%=c.getId()%>"><%=c.getName()%></option>
                    <%}%>

                </select>
            </div>
        </div>

        <div><input type="submit" value="Create"></div>
    </form>

</body>
</html>