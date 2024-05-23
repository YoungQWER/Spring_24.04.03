<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="includes/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>결제 페이지</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script>
    $(function(){
        var IMP = window.IMP;
        IMP.init('imp26828762'); // "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '${productName}',
            amount : '${amount}',
            buyer_email : '${Email}',
            buyer_name : '${Name}',
            buyer_addr : '${ShippingAddress}',
            buyer_postcode : '${ShippingPostalCode}',
        }, function(rsp) {
            if ( rsp.success ) {
                jQuery.ajax({
                    url: "/live/complete",
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid,
                        merchant_uid: rsp.merchant_uid,
                        paid_amount: rsp.paid_amount,
                        apply_num: rsp.apply_num
                    }
                }).done(function(data) {
                    msg = '결제가 완료되었습니다.';
                    msg += '\n고유ID : ' + rsp.imp_uid;
                    msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                    msg += '\n결제 금액 : ' + rsp.paid_amount + '원';
                    msg += '\n카드 승인번호 : ' + rsp.apply_num;
                    
                    alert(msg);
                    location.href='<%=request.getContextPath()%>/live/paySuccess?msg='+encodeURIComponent(msg)+'&merchant_uid='+rsp.merchant_uid+'&paid_amount='+rsp.paid_amount+'&apply_num='+rsp.apply_num;
                }).fail(function() {
                    alert('결제 정보를 서버에 전송하는데 실패했습니다.');
                    location.href="<%=request.getContextPath()%>/live/payFail";
                });
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '\n에러내용 : ' + rsp.error_msg;
                alert(msg);
                location.href="<%=request.getContextPath()%>/live/payFail";
            }
        });
    });
    </script>
</head>
<body>
    <h1>결제 처리 중입니다...</h1>
</body>
</html>

<%@ include file="includes/footer.jsp" %>
