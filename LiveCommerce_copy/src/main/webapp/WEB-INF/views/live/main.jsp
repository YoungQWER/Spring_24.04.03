<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.zerock.service.ProductService"%>
<%@page import="org.zerock.domain.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="org.zerock.service.LiveStreamService"%>
<%@page import="org.zerock.domain.LiveStreamVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp" %>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f7f7;
    }

    main {
        padding: 20px;
    }

    h1 {
        color: #333;
    }

    form {
        margin-bottom: 20px;
    }

    label {
        margin-right: 10px;
    }

    input[type="text"] {
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        padding: 5px 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }

    .product-grid {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .product-card {
        background-color: white;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        width: calc(20% - 20px);
        box-sizing: border-box;
    }

    .product-card img {
        width: 100%;
        height: auto;
        display: block;
    }

    .product-card-content {
        padding: 15px;
    }

    .product-card h2 {
        font-size: 18px;
        margin: 0 0 10px;
        color: #333;
    }

    .product-card p {
        margin: 0 0 10px;
        color: #666;
        font-size: 14px;
    }

    .product-card .price {
        font-size: 16px;
        color: #e91e63;
        font-weight: bold;
    }

    .product-card .view-details {
        display: inline-block;
        margin-top: 10px;
        padding: 8px 12px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 4px;
    }

    .product-card .view-details:hover {
        background-color: #45a049;
    }
</style>

<main>
    <h1>Products</h1>
    <form action="/live/search" method="get">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName">
        <button type="submit">Search</button>
    </form>
    <div class="product-grid">
        <c:forEach items="${products}" var="product">
            <div class="product-card">
                <img src="${product.photo}" alt="${product.productName}">
                <div class="product-card-content">
                    <h2>${product.productName}</h2>
                    <p>${product.description}</p>
                    <div class="price">${product.price}</div>
                    <a href="/live/product?id=${product.productId}" class="view-details">View Details</a>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<%@ include file="../includes/footer.jsp" %>