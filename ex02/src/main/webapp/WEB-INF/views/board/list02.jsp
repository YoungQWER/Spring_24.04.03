<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	
<div class="container">
  <h2>List Page</h2>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>BNO</th>
        <th>TITLE</th>
        <th>CONTENT</th>
        <th>WRITER</th>
        <th>REGDATE</th>
        <th>UPDATEDATE</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="board" items="${list}">
      <tr>
        <td>${board.bno}</td>
        <td>${board.title}</td>
        <td>${board.content}</td>
        <td>${board.writer}</td>
        <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
        <td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
        </c:forEach>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>