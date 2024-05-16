<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.zerock.service.ProductService"%>
<%@page import="org.zerock.domain.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="org.zerock.service.LiveStreamService"%>
<%@page import="org.zerock.domain.LiveStreamVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>라이브 커머스</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        nav ul li {
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            color: #fff;
            text-decoration: none;
        }
        main {
            padding: 20px;
        }
        .hero {
            background-color: #f4f4f4;
            padding: 50px;
            text-align: center;
        }
        .hero h2 {
            font-size: 36px;
            margin-bottom: 20px;
        }
        .hero p {
            font-size: 18px;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #555;
        }
        footer {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }
        /* 추가된 스타일 */
        .video-container {
            position: relative;
            width: 228px; /* 가로 크기 조정 */
            height: 342px; /* 세로 크기 조정 */
            margin: auto;
            margin-bottom: 20px;
        }
        .video-container video {
            width: 100%;
            height: 100%;
        }
        .product-id {
            font-size: 20px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>라이브 커머스</h1>
    <nav>
        <ul>
            <!-- 사용자 및 관리자에 따라 다른 메뉴 표시 -->
            <% if (request.isUserInRole("ROLE_USER")) { %>
                <li><a href="/live/profile">내 프로필</a></li>
                <li><a href="/cart/list">장바구니</a></li>
                <form action="/customLogout" method="post">
                    <button type="submit">로그아웃</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            <% } else if (request.isUserInRole("ROLE_ADMIN")) { %>
                <li><a href="/admin/dashboard">관리자 대시보드</a></li>
                <form action="/user/logout" method="post">
                    <button type="submit">로그아웃</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            <% } else { %>
                <li><a href="/customLogin">로그인</a></li>
                <li><a href="/user/register">회원가입</a></li>
            <% } %>
        </ul>
    </nav>
</header>



<main>
   <h1>Products</h1>
       <table border="1">
           <tr>
               <th>Product ID</th>
               <th>Product Name</th>
               <th>Description</th>
               <th>Price</th>
               <th>Photo</th>
           </tr>
           <c:forEach items="${products}" var="product">
               <tr>
                   <td>${product.productID}</td>
                   <td>${product.productName}</td>
                   <td>${product.description}</td>
                   <td>${product.price}</td>
                   <td><img src="${product.photo}" alt="${product.productName}" width="100"></td>
                   <td><a href="/live/product?id=${product.productID}">View Details</a></td>
               </tr>
           </c:forEach>
      </table>
</main>



<footer>
    <p>&copy; 2024 Live Commerce. All rights reserved.</p>
</footer>
</body>
</html>