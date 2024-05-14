<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart List</title>
</head>
<body>
    <h2>Cart List</h2>
    <table border="1">	
        <thead>
            <tr>
                <th>Cart ID</th>
                <th>User ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${carts}" var="cart">        	
                <tr>
                    <td>${cart.cartID}</td>
                    <td>${cart.userID}</td>
                    <td>${cart.productID}</td>
                    <td>${cart.quantity}</td>
                    <td><a href="/carts/${cart.cartID}">View Details</a></td>
                </tr>
                <tr>
                  <td>${product}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/carts/new">Add New Cart</a>
</body>
</html>