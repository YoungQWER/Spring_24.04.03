<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>장바구니</title>
</head>
<body>
    <h2>장바구니</h2>
    
    <form id="cartForm" action="/live/checkout" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th><input type="checkbox" id="selectAllCheckbox"> 전체 선택</th>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>총 가격</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="totalPrice" value="0" />
                <c:forEach items="${cartDetails}" var="cartItem">
                    <tr>
                        <td>
                            <input type="checkbox" class="productCheckbox" 
                                   data-product-name="${cartItem.productName}"
                                   data-total-price="${cartItem.price * cartItem.quantity}">
                        </td>
                        
                        <td>${cartItem.productName}</td>
                        <td>${cartItem.price}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.price * cartItem.quantity}</td>
                    </tr>
                    <%-- 각 상품의 총 가격을 totalPrice에 누적 --%>
                    <c:set var="totalPrice" value="${totalPrice + (cartItem.price * cartItem.quantity)}" />
                </c:forEach>
            </tbody>
        </table>

        <!-- 선택된 상품 결제하기 버튼 -->
        <button id="requestPaymentBtn" type="button">선택된 상품 결제하기</button>

        <%-- 선택된 상품 정보를 전송하기 위한 hidden 필드들 --%>
        <input type="hidden" name="totalAmount" value="${totalPrice}">
        <input type="hidden" name="selectedProducts" id="selectedProducts">
        <input type="hidden" name="selectedAmount" id="selectedAmount">
    </form>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // 전체 선택 체크박스 클릭 시 모든 상품 체크박스 상태 변경
            $('#selectAllCheckbox').click(function() {
                $('.productCheckbox').prop('checked', $(this).prop('checked'));
            });

            $('#requestPaymentBtn').click(function() {
                var totalPrice = 0;
                var selectedProducts = [];
                $('.productCheckbox:checked').each(function() {
                    var productName = $(this).data('product-name');
                    var price = $(this).data('total-price');
                    totalPrice += parseFloat(price); // 총 가격 누적
                    selectedProducts.push(productName);
                });
                $('#selectedProducts').val(selectedProducts.join(';'));
                $('#selectedAmount').val(totalPrice.toFixed(2)); // 총 가격을 소수점 2자리까지 반올림하여 전송
                $('#cartForm').submit();
            });
        });
    </script>
</body>
</html>
