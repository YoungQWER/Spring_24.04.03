<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>라이브 커머스</title>
    <style>
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
            display: inline;
            margin-right: 20px;
        }
        nav ul li a {
            color: #fff;
            text-decoration: none;
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
    </style>
</head>
<body>
    <header>
        <h1>라이브 커머스</h1>
        <nav>
            <ul>
                <li><a href="/home">홈</a></li>
                <li><a href="/products">상품 목록</a></li>
                <li><a href="/about">회사 소개</a></li>
                <!-- 사용자 및 관리자에 따라 다른 메뉴 표시 -->
                <%-- 로그인 여부에 따라 다른 메뉴 표시 --%>
                <% if (request.isUserInRole("ROLE_USER")) { %>
                    <li><a href="/profile">내 프로필</a></li>
                    <li><a href="/cart">장바구니</a></li>
                    <li><a href="/logout">로그아웃</a></li>
                <% } else if (request.isUserInRole("ROLE_ADMIN")) { %>
                    <li><a href="/admin/dashboard">관리자 대시보드</a></li>
                    <li><a href="/logout">로그아웃</a></li>
                <% } else { %>
                    <li><a href="/login">로그인</a></li>
                    <li><a href="/register">회원가입</a></li>
                <% } %>
            </ul>
        </nav>
    </header>
    
    <main>
        <section class="hero">
            <h2>Welcome to Live Commerce!</h2>
            <p>Discover amazing products and shop with ease.</p>
            <a href="/products" class="btn">Shop Now</a>
        </section>
    </main>
    
    <footer>
        <p>&copy; 2024 Live Commerce. All rights reserved.</p>
    </footer>
</body>
</html>