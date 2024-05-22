<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="../includes/header.jsp" %>


<body>
    <title>카테고리 페이지</title>
    <h1>카테고리 별 상품 목록</h1>
    
    <%-- 여기서 products는 컨트롤러에서 모델에 추가된 이름입니다. --%>
    <%-- 해당 카테고리에 속하는 상품들을 반복문으로 표시합니다. --%>
    <table border="1">
        <thead>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Photo</th>
            </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
</body>
</html>

<%@ include file="../includes/footer.jsp" %>