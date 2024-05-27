<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        button {
            padding: 8px 16px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #555;
        }

        input[type="checkbox"] {
            transform: scale(1.5); /* 체크박스 크기 조정 */
            margin-right: 5px;
        }

        #requestPaymentBtn {
            margin-top: 20px;
            float: right; /* 우측 정렬 */
        }

        .delete-btn {
            background-color: #ff4d4d; /* 빨간색 배경색 */
        }

        .delete-btn:hover {
            background-color: #ff6666; /* 밝은 빨간색 배경색 */
        }
    </style>
</head>
<body>
    <h2>장바구니</h2>
    <table border="1">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAllCheckbox"> 전체 선택</th>
                <th>상품명</th>
                <th>가격</th>
                <th>수량</th>
                <th>총 가격</th>
                <th>삭제</th>
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
                    <td><button class="delete-btn" data-cart-id="${cartItem.cartID}">삭제</button></td>
                </tr>
                <%-- 각 상품의 총 가격을 totalPrice에 누적 --%>
                <c:set var="totalPrice" value="${totalPrice + (cartItem.price * cartItem.quantity)}" />
            </c:forEach>
        </tbody>
    </table>
    <!-- 선택된 상품 결제하기 버튼 -->
    <button id="requestPaymentBtn" type="button">선택된 상품 결제하기</button>

    <%-- 선택된 상품 정보를 전송하기 위한 hidden 필드들 --%>
    <form id="cartForm" action="/live/checkout" method="post">
        <input type="hidden" name="totalAmount" value="${totalPrice}">
        <input type="hidden" name="selectedProducts" id="selectedProducts">
        <input type="hidden" name="selectedAmount" id="selectedAmount">
    </form>

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

        // 삭제 버튼 클릭 시
        $('.delete-btn').click(function() {
            var cartID = $(this).data('cart-id'); // 카트 아이디
            var $row = $(this).closest('tr'); // 현재 행 선택
            $.ajax({
                url: '/cart/remove',
                method: 'POST',
                data: {cartID: cartID},
                success: function(response) {
                    // 성공적으로 삭제되면 해당 상품 행을 제거
                    $row.remove();
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        });
    });
    </script>

</body>
</html>

<%@ include file="../includes/footer.jsp" %>
