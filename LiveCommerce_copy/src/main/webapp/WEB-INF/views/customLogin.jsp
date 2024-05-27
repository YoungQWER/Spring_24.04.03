<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<title>Custom Login Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
        text-align: center;
    }
    h1 {
        color: #333;
        margin-bottom: 20px;
    }
    h2 {
        color: red;
        margin-bottom: 20px;
    }
    .form-group {
        margin-bottom: 15px;
    }
    input[type="text"],
    input[type="password"] {
        width: calc(100% - 22px);
        padding: 10px;
        margin: 0 auto;
        display: block;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }
    input[type="checkbox"] {
        margin-right: 10px;
    }
    label {
        color: #555;
    }
    button {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        border: none;
        border-radius: 5px;
        color: white;
        font-size: 16px;
        cursor: pointer;
    }
    button:hover {
        background-color: #45a049;
    }
    .footer {
        text-align: center;
        margin-top: 20px;
        color: #aaa;
    }
    
    .register-link {
    color: #555;
    text-decoration: none;
    display: inline-block;
    margin-left: auto; /* 왼쪽 여백을 auto로 설정하여 오른쪽으로 이동 */
	}
	
	.register-link:hover {
	    color: #333;
	}
	
	.form-group {
	    margin-bottom: 15px;
	    display: flex;
	    justify-content: space-between; /* Remember Me와 회원가입을 오른쪽에 붙이기 위해 flex 속성 사용 */
	}
	    
</style>
</head>
<body>

	<div class="container">
	    <h1>Custom Login Page</h1>
	    <h2>${error}</h2>
	    <h2>${logout}</h2>
	
	    <form action="/login" method="post">
	        <div class="form-group">
	            <input type="text" name="username" value="qwer" placeholder="Username">
	        </div>
	        <div class="form-group">
	            <input type="password" name="password" value="qwer" placeholder="Password">
	        </div>
	        <div class="form-group">
	            <label>
	                <input type="checkbox" name="remember-me"> Remember Me
	            </label>
	            <a href="/user/register" class="register-link">회원가입</a>
	        </div>
	        <div class="form-group">
	            <button type="submit">Login</button>
	        </div>
	        
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    </form>
	</div>

</body>
</html>
