<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%@ include file="../includes/header.jsp" %>

<html>
<body>
<header>
    <!-- 상단 내비게이션 바 또는 로고 등을 여기에 추가 -->
</header>

<main>
    <h1>카테고리별 상품 목록</h1>
    <div class="product-list">
        <!-- 상품 목록을 표시하기 위한 반복문 -->
        <c:forEach items="${products}" var="product">
            <div class="product">
                <h2>${product.productName}</h2>
                <p>${product.description}</p>
                <p>${product.price}원</p>
                <!-- 상품의 사진을 표시 -->
                <img src="${product.photo}" alt="${product.productName}" width="200">
                <!-- 각 상품의 상세 페이지로 이동할 수 있는 링크 -->
                <a href="/live/product?id=${product.productID}">상세 정보 보기</a>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

<%@ include file="../includes/footer.jsp" %>