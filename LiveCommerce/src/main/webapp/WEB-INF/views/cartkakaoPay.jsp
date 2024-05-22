<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="includes/header.jsp" %>

<html>

<body>

    <meta charset="UTF-8">
    <title>카카오페이 결제</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    
    <h2>카카오페이 결제</h2>
    
    <h3>선택된 상품</h3>
    <ul>
        <c:forEach var="product" items="${selectedProducts}">
            <li>${product}</li>
        </c:forEach>
    </ul>

    <script>
        $(function() {
            var IMP = window.IMP; // 생략가능
            IMP.init('imp26828762'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
            var msg;

            IMP.request_pay({
                pg: 'kakaopay',
                pay_method: 'card',
                merchant_uid: 'merchant_' + new Date().getTime(),
                name: '${selectedProducts[0]}', // 첫 번째 상품명 (여러 상품명을 합쳐서 전달할 수도 있습니다)
                amount: '${amount}', // 총 결제 금액을 서버에서 계산하여 전달
                buyer_email: '${Email}',
                buyer_name: '${Name}',
                buyer_addr: '${ShippingAddress}',
                buyer_postcode: '${ShippingPostalCode}'
            }, function(rsp) {
                if (rsp.success) {
                    jQuery.ajax({
                        url: "/live/complete",
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            imp_uid: rsp.imp_uid
                        }
                    }).done(function(data) {
                        if (data === "Payment successfully processed.") {
                            msg = '결제가 완료되었습니다.';
                            msg += '\n고유ID : ' + rsp.imp_uid;
                            msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                            msg += '\n결제 금액 : ' + rsp.paid_amount;
                            msg += '\n카드 승인번호 : ' + rsp.apply_num;

                            alert(msg);
                            location.href = '<%=request.getContextPath()%>/live/paySuccess?msg=' + msg;
                        } else {
                            msg = '결제에 실패하였습니다.';
                            alert(msg);
                            location.href = '<%=request.getContextPath()%>/live/payFail?error_msg=' + rsp.error_msg;
                        }
                    });
                } else {
                    msg = '결제에 실패하였습니다.';
                    msg += '에러내용 : ' + rsp.error_msg;
                    alert(msg);
                    location.href = '<%=request.getContextPath()%>/live/payFail?error_msg=' + rsp.error_msg;
                }
            });
        });
    </script>
</body>
</html>

<%@ include file="includes/footer.jsp" %>