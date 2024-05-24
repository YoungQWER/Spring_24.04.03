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
        .category-icon {
            width: 32px; /* 아이콘 크기 */
            height: 32px;
            display: block; /* 블록 요소로 변경 */
            margin: 0 auto 5px; /* 아래쪽 마진 추가 */
        }
    </style>
</head>
<body>
<header>
    <h1><a href="/live/main" class="logo-link">라이브 커머스</a></h1>
    <nav class="main-nav">
        <ul>
            <%-- 카테고리 목록 표시 --%>
            <c:forEach items="${categories}" var="category" varStatus="status">
                <li>
                    <a href="/live/category?categoryId=${category.categoryID}">
                        <img src="<c:url value='/resources/images/icons/${status.index + 1}.png' />" alt="${category.categoryName}" class="category-icon">
                        ${category.categoryName}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
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
