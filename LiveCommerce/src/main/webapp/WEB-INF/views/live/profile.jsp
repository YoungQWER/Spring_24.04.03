<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>프로필</title>
    <!-- 부트스트랩 링크 추가 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 아이콘 라이브러리 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background-color: #f4f7f6;
            font-family: 'Arial', sans-serif;
        }
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            background-color: #ffffff;
        }
        .card-body {
            padding: 2rem;
            position: relative;
        }
        .card-body .profile-icon {
            position: absolute;
            top: -40px;
            left: 50%;
            transform: translateX(-50%);
            background-color: #007bff;
            color: white;
            border-radius: 50%;
            padding: 20px;
            font-size: 40px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .modal-header {
            background-color: #007bff;
            color: white;
        }
        .modal-footer .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .modal-content {
            border-radius: 10px;
        }
        .form-group label {
            font-weight: bold;
        }
    </style>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<div class="container">
    <div class="card text-center">
        <div class="card-body">
            <div class="profile-icon">
                <i class="fas fa-user"></i>
            </div>
            <h3 class="mt-5 mb-4">프로필</h3>

            <c:if test="${empty user}">
                <div class="alert alert-warning" role="alert">
                    사용자 정보를 불러올 수 없습니다.
                </div>
            </c:if>

            <c:if test="${not empty user}">
                <p><strong>사용자 아이디:</strong> ${user.username}</p>
                <p><strong>이메일:</strong> ${user.email}</p>
                <p><strong>배송 주소:</strong> ${user.shippingAddress}</p>
                <p><strong>배송 우편번호:</strong> ${user.shippingPostalCode}</p>
                <!-- 개인정보 수정 버튼 추가 -->
                <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#profileModal">개인정보 수정</button>
            </c:if>
        </div>
    </div>
</div>

<!-- 개인정보 수정 모달 창 -->
<div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profileModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="profileModalLabel">개인정보 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- 개인정보 입력 폼 -->
        <form id="profileForm" action="/user/updateProfile" method="post">
          <div class="form-group">
            <label for="email">이메일:</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
          </div>
          <div class="form-group">
            <label for="shippingAddress">배송 주소:</label>
            <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" value="${user.shippingAddress}" required>
          </div>
          <div class="form-group">
            <label for="shippingPostalCode">배송 우편번호:</label>
            <input type="text" class="form-control" id="shippingPostalCode" name="shippingPostalCode" value="${user.shippingPostalCode}" required>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="submit" class="btn btn-primary" form="profileForm">저장</button>
      </div>
    </div>
  </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

<%@ include file="../includes/footer.jsp" %>