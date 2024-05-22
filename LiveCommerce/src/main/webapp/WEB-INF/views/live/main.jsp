<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.zerock.service.ProductService"%>
<%@page import="org.zerock.domain.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="org.zerock.service.LiveStreamService"%>
<%@page import="org.zerock.domain.LiveStreamVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp" %>

<main>
    <h1>Products</h1>
    <form action="/live/search" method="get">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName">
        <button type="submit">Search</button>
    </form>
    <table border="1">
        <tr>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Photo</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td><img src="${product.photo}" alt="${product.productName}" width="100"></td>
                <td><a href="/live/product?id=${product.productId}">View Details</a></td>
            </tr>
        </c:forEach>
    </table>
</main>

<%@ include file="../includes/footer.jsp" %>
