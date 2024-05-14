<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 페이지</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
</head>
<body>
<header>
    <!-- 웹사이트 헤더 정보를 여기에 추가하세요 -->
</header>

<main>
    <h1>주문 페이지</h1>
    <div class="product-details">
        <h2>${product.productName}</h2>
        <p>제품 설명: ${product.description}</p>
        <p>가격: ${product.price}</p>
        <img src="${product.photo}" alt="${product.productName}" width="300">
    </div>
    <!-- 여기에 주문 폼 등을 추가할 수 있습니다 -->
</main>

<footer>
    <!-- 웹사이트 푸터 정보를 여기에 추가하세요 -->
</footer>
</body>
</html>
