<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 상세 정보</title>
    <!-- 여기에 필요한 CSS 및 JavaScript 파일을 추가하세요 -->
</head>
<body>
<header>
    <!-- 웹사이트 헤더 정보를 여기에 추가하세요 -->
</header>

<main>
    <h1>제품 상세 정보</h1>
    <!-- 제품 상세 정보를 표시하는 코드를 여기에 추가하세요 -->
    <div class="product-details">
        <h2>${product.productName}</h2>
        <p>제품 설명: ${product.description}</p>
        <p>가격: ${product.price}</p>
        <img src="${product.photo}" alt="${product.productName}" width="300">
        <form action="/live/order" method="get">
            <input type="hidden" name="id" value="${product.productID}">
            <button type="submit">구매하기</button>
        </form>
        <form action="/live/cart" method="get">
            <input type="hidden" name="id" value="${product.productID}">
            <button type="submit">장바구니</button>
        </form>
    </div>
</main>

<footer>
    <!-- 웹사이트 푸터 정보를 여기에 추가하세요 -->
</footer>
</body>
</html>
