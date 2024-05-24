<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        /* 기존 스타일 */
        .logo-link {
            color: #fff; /* 링크 색상 */
            text-decoration: none; /* 밑줄 제거 */
        }
        
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
            display: inline-block; /* 인라인 블록으로 변경 */
            margin-right: 20px;
            vertical-align: top; /* 세로 정렬 상단으로 */
        }
        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 14px; /* 글씨 크기를 14px로 줄임 */
            display: block; /* 블록 요소로 변경 */
            text-align: center; /* 텍스트 가운데 정렬 */
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
    <h1><a href="/live/main" class="logo-link">라이브 커머스</a></h1>
    <nav class="user-nav">
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

<html>
<body>

	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

    <script>
    $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp26828762'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
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
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/live/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
                //성공시 이동할 페이지
                location.href='<%=request.getContextPath()%>/live/paySuccess?msg='+msg;
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="<%=request.getContextPath()%>/live/payFail";
                alert(msg);
            }
        });
        
    });
    </script>
 
</body>
</html>

<%@ include file="includes/footer.jsp" %>